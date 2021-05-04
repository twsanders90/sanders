package com.nofluffkitchen.sanders.controllers;


import com.nofluffkitchen.sanders.data.UserRepository;
import com.nofluffkitchen.sanders.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/add-user")
public class UserController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String sendAddUserForm(Model model){
            model.addAttribute("user", new User());
        return "add-user";
    }

    @GetMapping("/view/{id}")
    public String viewUser(@PathVariable Long id, Model model){
        User user = this.userRepo.findById(id).get();
        model.addAttribute("user", user);
        return "view-user";
    }

    @PostMapping
    public String addUser(@Valid @ModelAttribute("user") User user, Errors errors){
        if (errors.hasErrors())
            return "add-user";

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Set.of(User.Role.ROLE_USER));
            user.setEnabled(true);
            this.userRepo.save(user);
        } catch (DataIntegrityViolationException e) {
            errors.rejectValue("email", "invalidEmail", "email already in use");
            return "add-user";
        }
        return "redirect:/view-user";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @Valid @ModelAttribute("user") User user, Errors errors){
        if (errors.hasErrors())
            return "view-user";

        try {
            User OriginalUser = this.userRepo.findById(id).get();
            updateOriginalUser(OriginalUser, user);
            this.userRepo.save(OriginalUser);
        } catch (DataIntegrityViolationException e) {
            errors.rejectValue("createdBy", "invalidUserName", "User Name already in use");
            return "view-user";
        }
        return "redirect:/add-user";
    }

    private void updateOriginalUser(User original, User update) {
        original.setFirstName(update.getFirstName());
        original.setLastName(update.getLastName());
        original.setUsername(update.getUsername());
        original.setEmail(update.getEmail());
        original.setPassword(update.getPassword());
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        this.userRepo.deleteById(id);
        return "redirect:/display-user";
    }


}
