package com.eduard.CourseWork.CW.Repositorys;

import com.eduard.CourseWork.CW.Models.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDAO extends CrudRepository<Document, Long> {

}
