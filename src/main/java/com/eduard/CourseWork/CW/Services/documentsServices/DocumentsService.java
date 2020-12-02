package com.eduard.CourseWork.CW.Services.documentsServices;

import com.eduard.CourseWork.CW.Models.Document;
import com.eduard.CourseWork.CW.Repositorys.DocumentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("documentsService")
public class DocumentsService {

    private final DocumentDAO documentDAO;

    @Autowired
    public DocumentsService(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    public List<Document> AllDocuments(){
        return (List<Document>) documentDAO.findAll();
    }
}
