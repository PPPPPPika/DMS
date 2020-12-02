package com.eduard.CourseWork.CW.Services.userService;

import com.eduard.CourseWork.CW.Models.User;
import com.eduard.CourseWork.CW.Repositorys.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User loadUserByLogin(String login){
        Optional<User> users = userDAO.findByLogin(login);
        User user = users.get();

        return user;
    }
}
