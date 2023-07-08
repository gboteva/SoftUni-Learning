package com.example.json_ex.services;

import com.example.json_ex.models.DTOs.CategoryWithCountOfProductAndProductsSumAndAvgDTO;
import com.example.json_ex.models.entities.Category;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedData() throws IOException;

    Set<Category> getRandomCategories();

    List<CategoryWithCountOfProductAndProductsSumAndAvgDTO> findAllCategoriesByCountOfTheirProducts();

}
