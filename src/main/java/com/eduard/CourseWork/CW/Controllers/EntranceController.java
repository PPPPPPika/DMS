package com.eduard.CourseWork.CW.Controllers;

import com.eduard.CourseWork.CW.Models.User;
import com.eduard.CourseWork.CW.Services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EntranceController {
    private final RegistrationService registrationService;

    @Autowired
    public EntranceController(@Qualifier("registrationService") RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") User user){
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistrationPage(@ModelAttribute("user") User user){
        if (registrationService.checkFieldsRegistration(user)){
            registrationService.insertUserInDB(user);
            return "redirect:/login";
        }
        else
            return "registration";
    }
}
