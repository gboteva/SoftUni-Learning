package com.example.json_ex.services;

import com.example.json_ex.models.DTOs.ProductInRangeDTO;
import com.example.json_ex.models.DTOs.ProductSeedDto;
import com.example.json_ex.models.entities.Product;
import com.example.json_ex.repositories.ProductRepository;
import com.example.json_ex.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    public static final String INPUT_PATH = "src/main/resources/files/products.json";
    private final ProductRepository productRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    private final Gson gson;

    private final UserService userService;

    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedData() throws IOException {
        if (productRepository.count() > 0){
            return;
        }

        String stringContent = Files.readString(Path.of(INPUT_PATH));

        ProductSeedDto[] productSeedDtos = gson.fromJson(stringContent, ProductSeedDto[].class);

        Arrays.stream(productSeedDtos).filter(validationUtil::isValid)
                .map(dto -> {
                    Product product = modelMapper.map(dto, Product.class);
                    product.setSeller(userService.getRandomUser());
                    product.setCategories(categoryService.getRandomCategories());

                    if (product.getName().length()%2 ==0) {
                        product.setBuyer(userService.getRandomUser());
                    }

                    return product;
                })
                .forEach(productRepository::save);
    }

    @Override
    public List<ProductInRangeDTO> saveInfoAboutProductsWithPriceInRangeWithNoBuyer() {
        List<Product> products = productRepository.findProductsByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        return products.stream()
                .map(product -> {
                    ProductInRangeDTO productDTO = modelMapper.map(product, ProductInRangeDTO.class);

                    productDTO.setSeller(product.getSeller().getFirstName() + " " + product.getSeller().getLastName());

                    return productDTO;
                })
                .collect(Collectors.toList());
    }
}
