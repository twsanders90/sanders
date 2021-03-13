package com.nofluffkitchen.sanders.controllers;

import com.nofluffkitchen.sanders.data.RecipeRepository;
import com.nofluffkitchen.sanders.models.Recipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/recipes")
public class RecipesController {

    private RecipeRepository recipeRepo;

    @Autowired
    public RecipesController(RecipeRepository recipeRepo){
        this.recipeRepo = recipeRepo;
    }

    @GetMapping
    public String addRecipe(Model model){
        model.addAttribute("recipes", new Recipes());
        return "add-recipe";
    }

    @PostMapping
    public String handleRecipeForm(@Valid @ModelAttribute("recipes")Recipes recipes, Errors errors){
        if (errors.hasErrors())
            return "add-recipe";

        this.recipeRepo.save(recipes);
        return "redirect:/recipes-view";
    }
}
