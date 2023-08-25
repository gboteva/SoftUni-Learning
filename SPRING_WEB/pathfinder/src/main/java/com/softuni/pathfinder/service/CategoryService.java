package com.softuni.pathfinder.service;

import com.softuni.pathfinder.model.entity.CategoryEntity;
import com.softuni.pathfinder.model.entity.enums.CategoryEnum;

public interface CategoryService {
    CategoryEntity getEntityByName(CategoryEnum name);
}
