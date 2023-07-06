package com.example.json_ex.services;

import com.example.json_ex.models.DTOs.CategorySeedDTO;
import com.example.json_ex.models.DTOs.CategoryWithCountOfProductAndProductsSumAndAvgDTO;
import com.example.json_ex.models.entities.Category;
import com.example.json_ex.repositories.CategoryRepository;
import com.example.json_ex.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final static String INPUT_PATH = "src/main/resources/files/categories.json";

    private final CategoryRepository categoryRepository;

    private final Gson gson;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedData() throws IOException {
        if (categoryRepository.count() > 0) {
            return;
        }

        String stringContent = Files.readString(Path.of(INPUT_PATH));

        CategorySeedDTO[] categorySeedDTOS = gson.fromJson(stringContent, CategorySeedDTO[].class);

        Arrays.stream(categorySeedDTOS)
                .filter(validationUtil::isValid)
                .map(dto -> modelMapper.map(dto, Category.class))
                .forEach(categoryRepository::save);

    }

    @Override
    public Set<Category> getRandomCategories() {
        int countCategories = ThreadLocalRandom.current().nextInt(1, 4);

        Set<Category> categories = new HashSet<>();
        long repositoryCount = categoryRepository.count();

        for (int i = 0; i <= countCategories; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, repositoryCount + 1);
            categories.add(categoryRepository.findById(randomId).orElse(null));
        }

        return categories;
    }

    @Override
    public List<CategoryWithCountOfProductAndProductsSumAndAvgDTO> findAllCategoriesByCountOfTheirProducts() {
        return categoryRepository.findAllByNumberOfProducts()
                .stream().map(cat -> modelMapper.map(cat, CategoryWithCountOfProductAndProductsSumAndAvgDTO.class))
                .collect(Collectors.toList());

    }
}
