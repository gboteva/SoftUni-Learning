package com.example.xml.services.impl;

import com.example.xml.models.DTO.ProductInRange.ProductInRangeDTO;
import com.example.xml.models.DTO.ProductInRange.ProductRootDTO;
import com.example.xml.models.DTO.SeedDTO.ProductSeedRootDTO;
import com.example.xml.models.entities.Product;
import com.example.xml.repositories.ProductRepository;
import com.example.xml.services.CategoryService;
import com.example.xml.services.ProductService;
import com.example.xml.services.UserService;
import com.example.xml.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    private final UserService userService;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public Long getCount() {
        return productRepository.count();
    }

    @Override
    public void seedData(ProductSeedRootDTO productSeedRootDTO) {
        productSeedRootDTO.getProducts().stream()
                .filter(validatorUtil::isValid)
                .map(productSeedDTO -> modelMapper.map(productSeedDTO, Product.class))
                .map(product -> {
                    product.setSeller(userService.getRandomUser());
                    if (product.getName().length() < 8){
                        product.setBuyer(userService.getRandomUser());
                    }

                    product.setCategories(categoryService.getRandomCategories());
                    return product;
                })
                .forEach(productRepository::save);
    }

    @Override
    public ProductRootDTO getProductWithNoBuyer() {
        List<Product> productsWithNoBuyer = productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal.valueOf(500),
                BigDecimal.valueOf(1000));

        ProductRootDTO productRootDTO = new ProductRootDTO();

        productRootDTO.setProducts(
                productsWithNoBuyer.stream()
                        .map(product -> {
                            ProductInRangeDTO dto = modelMapper.map(product, ProductInRangeDTO.class);
                            dto.setSeller(String.format("%s %s",
                                    product.getSeller().getFirstName() == null ? "":  product.getSeller().getFirstName(), product.getSeller().getLastName()));
                             return dto;
                        }).collect(Collectors.toList())
        );


        return productRootDTO;

    }
}
