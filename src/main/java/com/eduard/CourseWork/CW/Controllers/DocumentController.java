package com.eduard.CourseWork.CW.Controllers;

import com.eduard.CourseWork.CW.Models.Document;
import com.eduard.CourseWork.CW.Models.Previous_document;
import com.eduard.CourseWork.CW.Models.User;
import com.eduard.CourseWork.CW.Services.documentsServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/DCM")
public class DocumentController {

    private final CreateDocumentService createDocumentService;
    private final ViewDocumentService viewDocumentService;
    private final DownloadDocumentService downloadDocumentService;
    private final NewVersionService newVersionService;
    private final PreviousDocumentService previousDocumentService;

    @Autowired
    public DocumentController(@Qualifier("createDocumentService") CreateDocumentService createDocumentService,
                              @Qualifier("viewDocumentService") ViewDocumentService viewDocumentService,
                              @Qualifier("downloadDocumentService") DownloadDocumentService downloadDocumentService,
                              @Qualifier("newVersionService") NewVersionService newVersionService,
                              @Qualifier("previousDocumentService") PreviousDocumentService previousDocumentService) {
        this.createDocumentService = createDocumentService;
        this.viewDocumentService = viewDocumentService;
        this.downloadDocumentService = downloadDocumentService;
        this.newVersionService = newVersionService;
        this.previousDocumentService = previousDocumentService;
    }

    @GetMapping("/documents/new")
    public String getNewDocument(){
        return "createDocument";
    }

    @PostMapping("/documents/new")
    public String newDocument(@RequestParam String name,
                              @RequestParam String version,
                              @RequestParam ("file") MultipartFile multipartFile){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!createDocumentService.checkFieldDocument(name, version, multipartFile) ||
            !createDocumentService.checkOriginality(name)){

            return "createDocument";
        }
        else {
            try{
                createDocumentService.createDocument(name, auth.getName(), version, multipartFile);
                createDocumentService.transferFile(multipartFile);
                createDocumentService.insertDocumentInDB();

                return "redirect:/DCM/documents";
            } catch (IOException exception){
                return "createDocument";
            }
        }
    }

    @GetMapping("/documents/{id}")
    public String getDocument(@PathVariable("id") Long id, Model model){
        model.addAttribute("document", viewDocumentService.viewDocument(id));

        return "viewDocument";
    }

    @GetMapping("/documents/download/{fileName}")
    @ResponseBody
    public void downloadDocument(@PathVariable("fileName") String fileName, HttpServletResponse response){
        if (fileName.indexOf(".doc")>-1) response.setContentType("application/msword");
        if (fileName.indexOf(".docx")>-1) response.setContentType("application/msword");
        if (fileName.indexOf(".xls")>-1) response.setContentType("application/vnd.ms-excel");
        if (fileName.indexOf(".csv")>-1) response.setContentType("application/vnd.ms-excel");
        if (fileName.indexOf(".ppt")>-1) response.setContentType("application/ppt");
        if (fileName.indexOf(".pdf")>-1) response.setContentType("application/pdf");
        if (fileName.indexOf(".zip")>-1) response.setContentType("application/zip");

        response.setHeader("Content-Disposition", "attachment; filename=" + downloadDocumentService.outputDocumentName(viewDocumentService.searchByName(fileName).getPath()));
        response.setHeader("Content-Transfer-Encoding", "binary");

        try {
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            FileInputStream fis = new FileInputStream(createDocumentService.pathHolder + downloadDocumentService.outputDocumentName(viewDocumentService.searchByName(fileName).getPath()));

            int len;
            byte[] buf = new byte[1024];

            while((len = fis.read(buf)) > 0) {
                bos.write(buf,0,len);
            }

            bos.close();
            response.flushBuffer();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/documents/newVersion/{id}")
    public String getCreateNewVersion(@PathVariable("id") Long id, Model model){
        if (SecurityContextHolder.getContext().getAuthentication().getName().equals(viewDocumentService.viewDocument(id).getAuthor_document().getLogin())){
            model.addAttribute("idDocument", id);

            return "createNewVersionDocument";
        }
        else
            return "redirect:/DCM/documents";
    }

    @PostMapping("/documents/newVersion/{id}")
    public String createNewVersion(@RequestParam("file") MultipartFile multipartFile,
                                   @PathVariable("id") Long id){
        Document doc = viewDocumentService.viewDocument(id);

        if (!multipartFile.getOriginalFilename().equals(doc.getName())){
            Document documentNewVersion = newVersionService.createNewDocument(doc, multipartFile.getOriginalFilename());

            newVersionService.saveNewDocument(documentNewVersion);

            Document findDocument = newVersionService.findDocumentBySomeField(documentNewVersion.getName(),
                                                                              documentNewVersion.getCurrentVersion(),
                                                                              documentNewVersion.getPath());

            if (!doc.getPrevious_versions_document().isEmpty()) {
                List<Previous_document> pDoc = previousDocumentService.findByDocuments(doc);

                for (int i = 0; i < pDoc.size(); i++) {
                    pDoc.get(i).setCurrentDocument(documentNewVersion);
                    previousDocumentService.savePreviousDocument(pDoc.get(i));
                }
            }

            previousDocumentService.savePreviousDocument(previousDocumentService.createPreviousDocument(doc, findDocument));
            newVersionService.deleteDocument(doc);

            try {
                createDocumentService.transferFile(multipartFile);
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            return "redirect:/DCM/documents";
        }
        else
            return "createNewVersionDocument";
    }
}

    /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String author = auth.getName();*/