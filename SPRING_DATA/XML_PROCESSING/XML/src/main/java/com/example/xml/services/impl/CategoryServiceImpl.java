package com.example.xml.services.impl;

import com.example.xml.models.DTO.SeedDTO.CategorySeedRootDTO;
import com.example.xml.models.DTO.categoriesByProductCount.CategoryRootDTO;
import com.example.xml.models.DTO.categoriesByProductCount.CategoryWithProductInfoDTO;
import com.example.xml.models.entities.Category;
import com.example.xml.repositories.CategoryRepository;
import com.example.xml.services.CategoryService;
import com.example.xml.util.ValidatorUtil;
import com.example.xml.util.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    private final ValidatorUtil validatorUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, XMLParser xmlParser, ValidatorUtil validatorUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void seedData(CategorySeedRootDTO categorySeedRootDTO) throws JAXBException, FileNotFoundException {
            categorySeedRootDTO.getCategories().stream()
                    .filter(validatorUtil::isValid)
                    .map(categorySeedDTO -> modelMapper.map(categorySeedDTO, Category.class))
                    .forEach(categoryRepository::save);
    }

    @Override
    public long getCount() {
        return categoryRepository.count();
    }

    @Override
    public Set<Category> getRandomCategories() {
        long count = categoryRepository.count();

        Set<Category> categories = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, count + 1);
            categories.add(categoryRepository.findById(randomId).orElse(null));
        }

        return categories;

    }

    @Override
    public CategoryRootDTO getCategoriesWithProductInfo() {
        List<Category> categories = categoryRepository.findAll();
        CategoryRootDTO categoryRootDTO = new CategoryRootDTO();

        categoryRootDTO.setCategories(
                    categories.stream()
                            .map(category -> {
                                CategoryWithProductInfoDTO dto = modelMapper.map(category, CategoryWithProductInfoDTO.class);
                                dto.setProductCount(category.getProducts().size());
                                dto.setAveragePrice(category.getAvg());
                                dto .setTotalRevenue(category.getSum());
                                return dto;
                            }).collect(Collectors.toList())
        );

        return categoryRootDTO;
    }
}
