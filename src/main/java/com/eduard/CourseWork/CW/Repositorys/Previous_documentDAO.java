package com.eduard.CourseWork.CW.Repositorys;

import com.eduard.CourseWork.CW.Models.Previous_document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Previous_documentDAO extends CrudRepository<Previous_document, Long> {

}
