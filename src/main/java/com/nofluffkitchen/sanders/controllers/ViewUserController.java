package com.nofluffkitchen.sanders.controllers;

import com.nofluffkitchen.sanders.data.UserRepository;
import com.nofluffkitchen.sanders.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view-user")
public class ViewUserController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public String showUser(@ModelAttribute("user") User user){
        return "view-user";
    }
}
