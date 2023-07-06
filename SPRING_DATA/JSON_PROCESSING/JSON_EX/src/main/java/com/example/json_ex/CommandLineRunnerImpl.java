package com.example.json_ex;

import com.example.json_ex.models.DTOs.CategoryWithCountOfProductAndProductsSumAndAvgDTO;
import com.example.json_ex.models.DTOs.ProductInRangeDTO;
import com.example.json_ex.models.DTOs.UserSoldDTO;
import com.example.json_ex.models.DTOs.UserWithNamesAgeAndProductsDTO;
import com.example.json_ex.services.CategoryService;
import com.example.json_ex.services.ProductService;
import com.example.json_ex.services.UserService;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    public static final String OUTPUT_PATH = "src/main/resources/outFiles/";
    public static final String OUTPUT_PRODUCT_IN_RANGE = "product-in-range.json";
    public static final String OUTPUT_USERS_WITH_SOLD_PRODUCTS = "user-with-sold-product.json";
    public static final String OUTPUT_CATEGORIES_BY_PRODUCT_COUNT = "categories-by-products-count";
    public static final String OUTPUT_USERS_WITH_AT_LEAST_ONE_SOLD_PRODUCTS = "user-with-at-least-one - sold-products";
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final Gson gson;

    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService, ProductService productService, Gson gson) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        seedDataToDB();

        System.out.println("Enter exercise number: ");
        int number = Integer.parseInt(scanner.nextLine());

        switch (number) {
            case 1:
                productInRange();
                break;
            case 2:
                successfullySoldProducts();
                break;
            case 3:
                categoriesByProductsCount();
                break;
            case 4:
                usersAndProducts();
                break;
        }
    }

    private void usersAndProducts() throws IOException {
        List<UserWithNamesAgeAndProductsDTO> users = userService.findAllUsersWithAtLeastOneSoldProduct();

        String content = gson.toJson(users);

        writeToFile(OUTPUT_PATH + OUTPUT_USERS_WITH_AT_LEAST_ONE_SOLD_PRODUCTS, content);
    }

    private void categoriesByProductsCount() throws IOException {
        List<CategoryWithCountOfProductAndProductsSumAndAvgDTO> categories =
                categoryService.findAllCategoriesByCountOfTheirProducts();

        String content = gson.toJson(categories);

        writeToFile(OUTPUT_PATH + OUTPUT_CATEGORIES_BY_PRODUCT_COUNT, content);
    }

    private void successfullySoldProducts() throws IOException {
        List<UserSoldDTO> usersWithTheirSoldProducts = userService.getUsersWithTheirSoldProducts();

        String content = gson.toJson(usersWithTheirSoldProducts);

        writeToFile(OUTPUT_PATH + OUTPUT_USERS_WITH_SOLD_PRODUCTS, content);
    }

    private void productInRange() throws IOException {

        List<ProductInRangeDTO> productInRangeDTOS = productService.saveInfoAboutProductsWithPriceInRangeWithNoBuyer();

        String content = gson.toJson(productInRangeDTOS);

        writeToFile(OUTPUT_PATH + OUTPUT_PRODUCT_IN_RANGE, content);

    }

    private void writeToFile(String pathToFile, String content) throws IOException {
        Files.write(Path.of(pathToFile), Collections.singleton(content));
    }

    private void seedDataToDB() throws IOException {
        categoryService.seedData();
        userService.seedData();
        productService.seedData();
    }
}
