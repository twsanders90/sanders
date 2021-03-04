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
@RequestMapping("/Members")
public class MembersController {

        @GetMapping
        public String showMemberForm(Model model){
            model.addAttribute("Members", new Members());
            return "add-member";
        }
        @PostMapping
        public String handleMemberForm(@ModelAttribute("Members") Members Members, RedirectAttributes attributes){

            attributes.addFlashAttribute("fullProfile", Members.getFirstName() + "  " + '\'' +
            Members.getLastName() + "  " + '\'' + Members.getEmail() + "  " + '\'' + Members.getUserName() + "  " + '\'' +
                    Members.getAge());
            System.out.println("First Name: " + Members.getFirstName());
            System.out.println();
            System.out.println("Last Name: " + Members.getLastName());
            System.out.println();
            System.out.println("Email: " + Members.getEmail());
            System.out.println();
            System.out.println("User Name: " + Members.getUserName());
            System.out.println();
            System.out.println("Age: " + Members.getAge());
            return "redirect:/view";

        }
}
