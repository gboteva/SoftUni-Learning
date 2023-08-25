package com.exam.coffeeshop.init;


import com.exam.coffeeshop.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitCategory implements CommandLineRunner {
    private final CategoryService categoryService;

    public InitCategory(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initializeCategory();
    }
}
