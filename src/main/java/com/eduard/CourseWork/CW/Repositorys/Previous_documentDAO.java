package com.eduard.CourseWork.CW.Repositorys;

import com.eduard.CourseWork.CW.Models.Document;
import com.eduard.CourseWork.CW.Models.Previous_document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Previous_documentDAO extends CrudRepository<Previous_document, Long> {
    List<Previous_document> findByCurrentDocument(Document document);
    List<Previous_document> findAll();
}
