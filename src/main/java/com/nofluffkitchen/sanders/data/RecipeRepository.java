package com.nofluffkitchen.sanders.data;

import com.nofluffkitchen.sanders.models.Recipes;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipes, Long> {

}
