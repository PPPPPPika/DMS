package com.eduard.CourseWork.CW.Services;

import com.eduard.CourseWork.CW.Models.Enums.Role;
import com.eduard.CourseWork.CW.Models.Enums.Status;
import com.eduard.CourseWork.CW.Models.User;
import com.eduard.CourseWork.CW.Repositorys.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("registrationService")
public class RegistrationService {
    private final UserDAO userDAO;

    @Autowired
    public RegistrationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    public boolean checkFieldsRegistration(User user){
        boolean bool = false;

        if (user.getLogin().matches("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,39}$") &
            user.getPassword().length() > 1 & user.getPassword().length() < 201 &
            user.getPassword().matches("^[a-zA-Z0-9]+$") &
            user.getEmail().matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")){
            bool = true;
        }

        return bool;
    }

    private User transformationFieldsUser(User user){
        String encodePassword = passwordEncoder().encode(user.getPassword());

        user.setPassword(encodePassword);
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);

        return user;
    }

    public void insertUserInDB(User user){
        userDAO.save(transformationFieldsUser(user));
    }
}
