package com.eduard.CourseWork.CW.Services;

import com.eduard.CourseWork.CW.Models.User;
import com.eduard.CourseWork.CW.Repositorys.UserDAO;
import com.eduard.CourseWork.CW.Security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String login_user) throws UsernameNotFoundException {
        User user = userDAO.findByLogin(login_user).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));

        return SecurityUser.fromUser(user);
    }
}
