package com.example.springintro.services;

import com.example.springintro.entities.Category;
import com.example.springintro.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService{
    private static final String CATEGORY_FILE_PATH = "src/main/resources/categories.txt";
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategory() throws IOException {

        if (categoryRepository.count() > 0){
            return;
        }

        Files.readAllLines(Path.of(CATEGORY_FILE_PATH))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach(name -> categoryRepository.save(new Category(name)));
    }

    @Override
    public Set<Category> getRandomCategories() {
        long randomCountCat = ThreadLocalRandom.current()
                .nextLong(1, 4);

        Set<Category> categories = new HashSet<>();

        long repositoryCount = categoryRepository.count();

        for (int i = 0; i < randomCountCat; i++) {
            long randomId = ThreadLocalRandom.current()
                    .nextLong(1, repositoryCount+1);

            Category category = categoryRepository.findById(randomId).orElse(null);

            categories.add(category);
        }

        return categories;
    }

}
