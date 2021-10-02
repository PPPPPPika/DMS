package com.eduard.CourseWork.CW.Services.documentsServices;

import com.eduard.CourseWork.CW.Models.Document;
import com.eduard.CourseWork.CW.Repositorys.DocumentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("newVersionService")
public class NewVersionService {
    private final String pathHolder = "C:/Users/edik_/holderDocuments/";

    private final DocumentDAO documentDAO;

    @Autowired
    public NewVersionService(DocumentDAO documentDAO) {
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

    public Document findDocumentBySomeField(String name, String version, String path){
        Optional<Document> collection = documentDAO.findByNameAndCurrentVersionAndPath(name, version, path);

        return collection.get();
    }

    public void deleteDocumentById(Long id){
        documentDAO.deleteById(id);
    }

    public void saveNewDocument(Document document){
        documentDAO.save(document);
    }
}
