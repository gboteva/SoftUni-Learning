package com.exam.coffeeshop.repository;

import com.exam.coffeeshop.model.entity.CategoryEntity;
import com.exam.coffeeshop.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(CategoryNameEnum category);
}
