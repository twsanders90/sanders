package com.nofluffkitchen.sanders.controllers;

import com.nofluffkitchen.sanders.models.Members;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewMemberController {

    @GetMapping
    public String showMember(@ModelAttribute("members") Members members, Model model){
        model.addAttribute("members", members);

        return "display-member";
    }
}
