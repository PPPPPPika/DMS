package com.eduard.CourseWork.CW.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/DCM")
public class FooterController {
    @GetMapping("/support")
    public String getSupportPage(){
        return "support";
    }

    @GetMapping("/contacts")
    public String getContactsPage(){
        return "contacts";
    }

    @GetMapping("/workers")
    public String getWorkersPage(){
        return "workers";
    }
}
