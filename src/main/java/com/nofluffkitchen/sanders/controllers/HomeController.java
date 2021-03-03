package com.nofluffkitchen.sanders.controllers;

import com.nofluffkitchen.sanders.models.Members;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHomePage(Model model) {

        model.addAttribute("members", new Members());
        //model.addAttribute("members", new Members("Tom","Smith","TomS@email.com", "Tommyboy123", 23));

        return "index";
    }
    //@PostMapping
    //public String memberForm(@ModelAttribute()){

    //}


}
