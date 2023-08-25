package com.exam.coffeeshop.service.Impl;

import com.exam.coffeeshop.model.entity.CategoryEntity;
import com.exam.coffeeshop.model.entity.enums.CategoryNameEnum;
import com.exam.coffeeshop.repository.CategoryRepository;
import com.exam.coffeeshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initializeCategory() {
        if (categoryRepository.count() > 0) {
            return;
        }

        Arrays.stream(CategoryNameEnum.values())
                .map(categoryName -> {
                    CategoryEntity category = new CategoryEntity();
                    category.setName(categoryName);
                    switch (categoryName.name()) {
                        case "Coffee" -> category.setNeededTime(2);
                        case "Cake" -> category.setNeededTime(10);
                        case "Drink" -> category.setNeededTime(1);
                        case "Other" -> category.setNeededTime(5);
                    }
                    return category;
                }).forEach(categoryRepository::save);
    }

    @Override
    public CategoryEntity findByName(CategoryNameEnum category) {
       return categoryRepository.findByName(category);
    }
}
