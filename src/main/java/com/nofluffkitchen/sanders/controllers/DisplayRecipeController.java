package com.nofluffkitchen.sanders.controllers;

import com.nofluffkitchen.sanders.models.Recipe;
import com.nofluffkitchen.sanders.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/display-recipes")
public class DisplayRecipeController {

    @Autowired
    private RecipeRepository recipeRepo;



    @GetMapping
    public String showRecipeList(Model model){
        List<Recipe> recipes = (List<Recipe>) this.recipeRepo.findAll();
        model.addAttribute("recipes", recipes);
        return "display-recipes";
    }
}
