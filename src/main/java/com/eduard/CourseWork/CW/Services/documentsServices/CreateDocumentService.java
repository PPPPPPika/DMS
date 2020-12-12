package com.eduard.CourseWork.CW.Services.documentsServices;

import com.eduard.CourseWork.CW.Models.Document;
import com.eduard.CourseWork.CW.Repositorys.DocumentDAO;
import com.eduard.CourseWork.CW.Services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service("createDocumentService")
public class CreateDocumentService {
    public final String pathHolder = "C:/Users/edik_/holderDocuments/";

    private final DocumentDAO documentDAO;
    private final UserService userService;

    private Document newDocument;

    @Autowired
    public CreateDocumentService(DocumentDAO documentDAO, UserService userService) {
        this.documentDAO = documentDAO;
        this.userService = userService;
    }

    public boolean checkOriginality(String name){
        return documentDAO.findByName(name).isEmpty();
    }

    public boolean checkFieldDocument(String name, String version, MultipartFile multipartFile){
        return multipartFile != null && !multipartFile.getOriginalFilename().isEmpty() &&
               name.matches("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,39}$") &&
               version.matches("\\d+") && version.length() > 0 && version.length() < 5;
    }

    public void transferFile(MultipartFile multipartFile) throws IOException {
        File uploadDir = new File(pathHolder);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        multipartFile.transferTo(new File(pathHolder + multipartFile.getOriginalFilename()));
    }

    public void createDocument(String name, String author, String version, MultipartFile multipartFile){
        Document document = new Document();

        document.setName(name);
        document.setAuthor_document(userService.loadUserByLogin(author));
        document.setCurrentVersion(version);
        document.setPath(pathHolder + multipartFile.getOriginalFilename());

        newDocument = document;
    }

    public void insertDocumentInDB(){
        documentDAO.save(newDocument);
    }
}
