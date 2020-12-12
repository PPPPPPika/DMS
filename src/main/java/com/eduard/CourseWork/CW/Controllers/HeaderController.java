package com.eduard.CourseWork.CW.Controllers;

import com.eduard.CourseWork.CW.Services.documentsServices.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/DCM")
public class HeaderController {
    private final DocumentsService documentsService;

    @Autowired
    public HeaderController(@Qualifier("documentsService") DocumentsService documentsService) {
        this.documentsService = documentsService;
    }

    @GetMapping("/documents")
    public String getDocumentsPage(Model model){
        model.addAttribute("documents", documentsService.AllDocuments());

        return "documents";
    }
}

