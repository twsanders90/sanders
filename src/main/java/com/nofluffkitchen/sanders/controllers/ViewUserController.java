package com.nofluffkitchen.sanders.controllers;

import com.nofluffkitchen.sanders.data.UserRepository;
import com.nofluffkitchen.sanders.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view-user")
public class ViewUserController {

    @GetMapping
    public String showUser(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "view-user";
    }

}
