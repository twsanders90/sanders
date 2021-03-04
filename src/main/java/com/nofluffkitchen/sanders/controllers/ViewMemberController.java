package com.nofluffkitchen.sanders.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewMemberController {

    @GetMapping
    public String showMember(@ModelAttribute("fullProfile") Object flashAttribute, Model model){
        model.addAttribute("fullProfile", flashAttribute);
        return "display-member";
    }
}
