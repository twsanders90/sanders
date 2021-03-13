package com.nofluffkitchen.sanders.controllers;

import com.nofluffkitchen.sanders.models.Recipes;
import com.nofluffkitchen.sanders.data.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/recipes-view")
public class ViewRecipeController {

    @Autowired
    private RecipeRepository recipeRepo;

//    @Autowired
//    public ViewRecipeController(RecipeRepository RecipeRepo){this.recipeRepo = recipeRepo;}

    @GetMapping
    public String showRecipeList(Model model){
        List<Recipes> recipe = (List<Recipes>) this.recipeRepo.findAll();
        model.addAttribute("recipes", recipe);
        return "display-recipe";
    }
}
