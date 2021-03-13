package com.nofluffkitchen.sanders.controllers;

import com.nofluffkitchen.sanders.models.Members;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/members")
public class MembersController {

        @GetMapping
        public String showMemberForm(Model model){
            model.addAttribute("members", new Members());
            return "add-member";
        }
        @PostMapping
        public String handleMemberForm(@ModelAttribute("members") Members members, RedirectAttributes attributes){

            attributes.addFlashAttribute("members", members);

            System.out.println("First Name: " + members.getFirstName());
            System.out.println();
            System.out.println("Last Name: " + members.getLastName());
            System.out.println();
            System.out.println("Email: " + members.getEmail());
            System.out.println();
            System.out.println("User Name: " + members.getUserName());
            System.out.println();
            System.out.println("Age: " + members.getAge());
            return "redirect:/members-view";

        }
}
