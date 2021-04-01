package com.nofluffkitchen.sanders.controllers;

import com.nofluffkitchen.sanders.data.CategoryRepository;
import com.nofluffkitchen.sanders.data.RecipeRepository;
import com.nofluffkitchen.sanders.models.Category;
import com.nofluffkitchen.sanders.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/recipe")
public class RecipesController {

    private RecipeRepository recipeRepo;
    private CategoryRepository categoryRepo;

    @Autowired
    public RecipesController(RecipeRepository recipeRepo, CategoryRepository categoryRepo){
        this.recipeRepo = recipeRepo;
        this.categoryRepo = categoryRepo;
    }

    @GetMapping
    public String sendAddRecipeForm(Model model){
        List<Category> categories = (List<Category>) categoryRepo.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("recipe", new Recipe());
        return "add-recipe";
    }

    @GetMapping("/view/{id}")
    public String viewRecipe(@PathVariable Long id, Model model){
        Recipe recipe = this.recipeRepo.findById(id).get();
        List<Category> categories = (List<Category>) categoryRepo.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("recipe", recipe);
        return "view-recipe";
    }

    @PostMapping
    public String addRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, Errors errors){
        if (errors.hasErrors())
            return "add-recipe";

        try {
            this.recipeRepo.save(recipe);
        } catch (DataIntegrityViolationException e) {
            errors.rejectValue("createdBy", "invalidUserName", "User Name already in use");
            return "add-recipe";
        }
        return "redirect:/display-recipes";
    }

    @PostMapping("/edit/{id}")
    public String editRecipe(@PathVariable Long id, @Valid @ModelAttribute("recipes") Recipe recipe, Errors errors){
        if (errors.hasErrors())
            return "view-recipe";

        try {
            Recipe originalRecipe = this.recipeRepo.findById(id).get();
            updateOriginalRecipe(originalRecipe, recipe);
            this.recipeRepo.save(originalRecipe);
        } catch (DataIntegrityViolationException e) {
            errors.rejectValue("createdBy", "invalidUserName", "User Name already in use");
            return "view-recipe";
        }
        return "redirect:/display-recipes";
    }

    private void updateOriginalRecipe(Recipe original, Recipe update){
        original.setName(update.getName());
        original.setDirections(update.getDirections());
        original.setIngredients(update.getIngredients());
        original.setType(update.getType());
        original.setCreatedBy(update.getCreatedBy());
        original.setCategory(update.getCategory());
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id){
        this.recipeRepo.deleteById(id);
        return "redirect:/display-recipes";
    }

}
