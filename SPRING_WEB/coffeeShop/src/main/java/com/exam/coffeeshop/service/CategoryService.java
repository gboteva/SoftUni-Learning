package com.exam.coffeeshop.service;

import com.exam.coffeeshop.model.entity.CategoryEntity;
import com.exam.coffeeshop.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    void initializeCategory();

    CategoryEntity findByName(CategoryNameEnum category);
}
