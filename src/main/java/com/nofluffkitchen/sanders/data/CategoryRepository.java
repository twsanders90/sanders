package com.nofluffkitchen.sanders.data;

import com.nofluffkitchen.sanders.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
