package com.softuni.pathfinder.service.Impl;

import com.softuni.pathfinder.model.entity.CategoryEntity;
import com.softuni.pathfinder.model.entity.enums.CategoryEnum;
import com.softuni.pathfinder.repository.CategoryRepository;
import com.softuni.pathfinder.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public CategoryEntity getEntityByName(CategoryEnum name) {
        return categoryRepository.findByName(name);
    }
}
