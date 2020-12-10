package com.eduard.CourseWork.CW.Services.documentsServices;

import com.eduard.CourseWork.CW.Models.Document;
import com.eduard.CourseWork.CW.Models.Previous_document;
import com.eduard.CourseWork.CW.Repositorys.Previous_documentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("previousDocumentService")
public class PreviousDocumentService {
    private final Previous_documentDAO previous_documentDAO;

    @Autowired
    public PreviousDocumentService(Previous_documentDAO previous_documentDAO) {
        this.previous_documentDAO = previous_documentDAO;
    }

    //док без текущей версии
    public Previous_document createPreviousDocument(Document document, Document newDocument){
        return new Previous_document(document.getName(),
                                     document.getAuthor_document().getLogin(),
                                     document.getCurrentVersion(),
                                     newDocument,
                                     document.getPath());
    }

    public boolean checkExistPreviousDocument(Previous_document previous_document){
        return previous_documentDAO.findById(previous_document.getId()).isPresent();//true если существует
    }

    public List<Previous_document> findByDocuments(Document document){
        return previous_documentDAO.findByCurrentDocument(document);
    }

    public void savePreviousDocument(Previous_document previous_document){
        previous_documentDAO.save(previous_document);
    }

}
