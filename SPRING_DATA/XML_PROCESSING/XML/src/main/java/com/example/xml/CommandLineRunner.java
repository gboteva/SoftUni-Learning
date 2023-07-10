package com.example.xml;

import com.example.xml.models.DTO.ProductInRange.ProductInRangeDTO;
import com.example.xml.models.DTO.ProductInRange.ProductRootDTO;
import com.example.xml.models.DTO.SeedDTO.CategorySeedRootDTO;
import com.example.xml.models.DTO.SeedDTO.ProductSeedRootDTO;
import com.example.xml.models.DTO.SeedDTO.UserSeedRootDTO;
import com.example.xml.models.DTO.categoriesByProductCount.CategoryRootDTO;
import com.example.xml.models.DTO.soldProduct.UserRootDTO;
import com.example.xml.services.CategoryService;
import com.example.xml.services.ProductService;
import com.example.xml.services.UserService;
import com.example.xml.util.XMLParser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    public static final String INPUT_PATH = "src/main/resources/input/";
    public static final String CATEGORY_INPUT = "categories.xml";
    public static final String USER_INPUT = "users.xml";
    public static final String PRODUCTS_INPUT = "products.xml";

    public static final String OUT_PATH = "src/main/resources/out/";
    public static final String PRODUCT_IN_RANGE = "products-in-range.xml";
    public static final String USER_WITH_SOLD_PRODUCT = "users-sold-products.xml";

    public static final String CATEGORY_WITH_PRODUCTS_INFO = "categories-by-products.xml";
    private final CategoryService categoryService;
    private final UserService userService;

    private final ProductService productService;
    private final XMLParser xmlParser;

    private Scanner scanner;

    public CommandLineRunner(CategoryService categoryService, UserService userService, ProductService productService, XMLParser xmlParser) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.xmlParser = xmlParser;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        seedDataToDB();

        System.out.println("Enter the number of task:");
        int number = Integer.parseInt(scanner.nextLine());

        switch (number){
            case 1 -> productInRange();
            case 2 -> soldProducts();
            case 3 -> categoriesByProductCount();
        }
    }

    private void categoriesByProductCount() throws JAXBException {
        CategoryRootDTO categoryRootDTO = categoryService.getCategoriesWithProductInfo();

        xmlParser.writeToFile(OUT_PATH + CATEGORY_WITH_PRODUCTS_INFO, categoryRootDTO);
    }

    private void soldProducts() throws JAXBException {
        UserRootDTO userRootDTO = userService.getUserWithSoldProduct();
        xmlParser.writeToFile(OUT_PATH + USER_WITH_SOLD_PRODUCT, userRootDTO);
    }

    private void productInRange() throws JAXBException {
        ProductRootDTO productWithNoBuyer = productService.getProductWithNoBuyer();

        xmlParser.writeToFile(OUT_PATH + PRODUCT_IN_RANGE, productWithNoBuyer);

    }

    private void seedDataToDB() throws JAXBException, FileNotFoundException {
        if (categoryService.getCount() == 0) {
            CategorySeedRootDTO categorySeedRootDTO = xmlParser.fromFile(INPUT_PATH + CATEGORY_INPUT,
                    CategorySeedRootDTO.class);

            categoryService.seedData(categorySeedRootDTO);
        }

        if (userService.getCount() == 0) {
            UserSeedRootDTO userSeedRootDTO = xmlParser.fromFile(INPUT_PATH + USER_INPUT, UserSeedRootDTO.class);

            userService.seedData(userSeedRootDTO);
        }


        if (productService.getCount() == 0){
            ProductSeedRootDTO productSeedRootDTO = xmlParser.fromFile(INPUT_PATH + PRODUCTS_INPUT, ProductSeedRootDTO.class);

            productService.seedData(productSeedRootDTO);
        }


    }
}
