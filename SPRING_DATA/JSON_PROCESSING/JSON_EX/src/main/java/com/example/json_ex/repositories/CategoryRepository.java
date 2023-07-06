package com.example.json_ex.repositories;

import com.example.json_ex.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
        @Query("select c from Category c ORDER BY size(c.products)")
        List<Category> findAllByNumberOfProducts();
}
