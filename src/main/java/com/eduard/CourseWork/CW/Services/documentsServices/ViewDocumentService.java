package com.eduard.CourseWork.CW.Services.documentsServices;

import com.eduard.CourseWork.CW.Models.Document;
import com.eduard.CourseWork.CW.Repositorys.DocumentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("viewDocumentService")
public class ViewDocumentService {
    private final DocumentDAO documentDAO;

    @Autowired
    public ViewDocumentService(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    public Document viewDocument(Long id){
        Optional<Document> document = documentDAO.findById(id);

        return document.get();
    }

    public Document searchByName(String nameDocument){
        Optional<Document> collection = documentDAO.findByName(nameDocument);

        return collection.get();
    }
}
