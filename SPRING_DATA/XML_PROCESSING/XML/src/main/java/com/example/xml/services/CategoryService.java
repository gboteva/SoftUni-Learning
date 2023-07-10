package com.example.xml.services;

import com.example.xml.models.DTO.SeedDTO.CategorySeedRootDTO;
import com.example.xml.models.DTO.categoriesByProductCount.CategoryRootDTO;
import com.example.xml.models.entities.Category;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Set;

public interface CategoryService {
    void seedData(CategorySeedRootDTO categorySeedRootDTO) throws JAXBException, FileNotFoundException;

    long getCount();

    Set<Category> getRandomCategories();


    CategoryRootDTO getCategoriesWithProductInfo();
}
