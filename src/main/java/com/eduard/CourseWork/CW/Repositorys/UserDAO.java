package com.eduard.CourseWork.CW.Repositorys;

import com.eduard.CourseWork.CW.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDAO extends CrudRepository<User, Long> {
    Optional<User> findByLogin(String login_user);
}
