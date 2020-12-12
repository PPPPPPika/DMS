package com.eduard.CourseWork.CW.Services.documentsServices;

import com.eduard.CourseWork.CW.Models.Document;
import com.eduard.CourseWork.CW.Models.Previous_document;
import com.eduard.CourseWork.CW.Repositorys.Previous_documentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("previousDocumentService")
public class PreviousDocumentService {
    private final Previous_documentDAO previous_documentDAO;

    @Autowired
    public PreviousDocumentService(Previous_documentDAO previous_documentDAO) {
        this.previous_documentDAO = previous_documentDAO;
    }

    public Previous_document createPreviousDocument(Document document, Document newDocument){
        return new Previous_document(document.getName(),
                                     document.getAuthor_document().getLogin(),
                                     document.getCurrentVersion(),
                                     newDocument,
                                     document.getPath());
    }

    public Previous_document findByIdPreviousDocument(Long id){
        Optional<Previous_document> previous_document = previous_documentDAO.findById(id);

        return previous_document.get();
    }

    public List<Previous_document> findByDocuments(Document document){
        return previous_documentDAO.findByCurrentDocument(document);
    }

    public void savePreviousDocument(Previous_document previous_document){
        previous_documentDAO.save(previous_document);
    }

    public void deletePreviousDocument(Long id){
        previous_documentDAO.deleteById(id);
    }

}
