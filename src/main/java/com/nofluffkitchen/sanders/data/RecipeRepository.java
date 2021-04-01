package com.nofluffkitchen.sanders.data;

import com.nofluffkitchen.sanders.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
