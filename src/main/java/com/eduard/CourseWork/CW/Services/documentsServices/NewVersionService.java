package com.eduard.CourseWork.CW.Services.documentsServices;

import com.eduard.CourseWork.CW.Models.Document;
import com.eduard.CourseWork.CW.Models.Previous_document;
import com.eduard.CourseWork.CW.Repositorys.DocumentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("newVersionService")
public class NewVersionService {
    private final String pathHolder = "C:/Users/edik_/holderDocuments/";

    private final PreviousDocumentService previousDocumentService;
    private final DocumentDAO documentDAO;

    @Autowired
    public NewVersionService(PreviousDocumentService previousDocumentService, DocumentDAO documentDAO) {
        this.previousDocumentService = previousDocumentService;
        this.documentDAO = documentDAO;
    }

    private String plusVersion(String version){
        return String.valueOf(Integer.parseInt(version) + 1);
    }

    public Document createNewDocument(Document document, String fileName){
        return new Document(document.getName(),
                            document.getAuthor_document(),
                            plusVersion(document.getCurrentVersion()),
                      pathHolder + fileName);

    }

//String name, User author_document, String currentVersion, List<Previous_document> previous_versions_document, String path

    public Document findDocumentBySomeField(String name, String version, String path){
        Optional<Document> collection = documentDAO.findByNameAndCurrentVersionAndPath(name, version, path);

        return collection.get();
    }

    //findByNameAndCurrentVersionAndPath



    public void deleteDocument(Document document){
        documentDAO.deleteById(document.getId());
    }

    public void saveNewDocument(Document document){
        documentDAO.save(document);
    }

}
