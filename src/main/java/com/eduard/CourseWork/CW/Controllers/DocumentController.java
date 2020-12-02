package com.eduard.CourseWork.CW.Controllers;

import com.eduard.CourseWork.CW.Services.documentsServices.CreateDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/DCM")
public class DocumentController {

    private final CreateDocumentService createDocumentService;

    @Autowired
    public DocumentController(@Qualifier("createDocumentService") CreateDocumentService createDocumentService) {
        this.createDocumentService = createDocumentService;
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
}

    /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String author = auth.getName();*/