package com.nofluffkitchen.sanders.controllers;

import com.nofluffkitchen.sanders.data.RecipeRepository;
import com.nofluffkitchen.sanders.models.Recipes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/view/{id}")
    public String showRecipe(@PathVariable Long id, Model model){
        Recipes recipe = this.recipeRepo.findById(id).get();
        model.addAttribute("recipes", recipe);
        return "view-recipe";

    }

    @PostMapping
    public String handleRecipeForm(@Valid @ModelAttribute("recipes")Recipes recipes, Errors errors){
        if (errors.hasErrors())
            return "add-recipe";

        try {
            this.recipeRepo.save(recipes);
        } catch (DataIntegrityViolationException e) {
            errors.rejectValue("createdBy", "invalidUserName", "User Name already in use");
            return "add-recipe";
        }
        return "redirect:/recipes-view";
    }

    @PostMapping("/edit/{id}")
    public String handleEditRecipeForm(@PathVariable Long id, @Valid @ModelAttribute("recipes")Recipes recipes, Errors errors){
        if (errors.hasErrors())
            return "redirect:/recipes-view";

        try {
            Recipes originalRecipe = this.recipeRepo.findById(id).get();
            updateOriginalRecipe(originalRecipe, recipes);
            this.recipeRepo.save(originalRecipe);
        } catch (DataIntegrityViolationException e) {
            errors.rejectValue("createdBy", "invalidUserName", "User Name already in use");
            return "/recipes/view";
        }
        return "redirect:/recipes-view";
    }
    private void updateOriginalRecipe(Recipes original, Recipes update){
        original.setName(update.getName());
        original.setDirections(update.getDirections());
        original.setIngredients(update.getIngredients());
        original.setType(update.getType());
        original.setCreatedBy(update.getCreatedBy());

    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id){
        this.recipeRepo.deleteById(id);
        return "redirect:/recipes-view";
    }

}
