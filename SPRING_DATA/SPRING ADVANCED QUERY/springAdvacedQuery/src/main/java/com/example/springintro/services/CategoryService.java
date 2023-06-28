package com.example.springintro.services;

import com.example.springintro.entities.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategory() throws IOException;

    Set<Category> getRandomCategories();

}
