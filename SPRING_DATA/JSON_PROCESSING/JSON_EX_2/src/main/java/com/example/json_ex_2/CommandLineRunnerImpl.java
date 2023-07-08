package com.example.json_ex_2;

import com.example.json_ex_2.models.DTO.SaleBaseInfo;
import com.example.json_ex_2.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    public static final String OUTPUT_PATH = "src/main/resources/out/";
    public static final String FILE_NAME = "ordered-customers.json";
    public static final String FILE_NAME_TOYOTA = "toyota-cars.json";

    public static final String FILE_NAME_LOCAL_SUPPLIER = "local-suppliers.json";
    public static final String FILE_NAME_CARS_AND_PARTS = "cars-and-parts.json";

    public static final String FILE_NAME_CUSTOMERS_INFO = "customers-total-sales.json";

    public static final String FILE_NAME_SALES_INFO = "sales-discounts.json";

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public CommandLineRunnerImpl(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        seedDataToDB();

        System.out.println("Enter the number of query task:");
        int number = Integer.parseInt(scanner.nextLine());

        switch (number) {
            case 1:
                orderedCustomers();
                break;
            case 2:
                carsFromMakeToyota();
                break;

            case 3:
                localSuppliers();
                break;

            case 4:
                carsWithTheirListOfParts();
                break;

            case 5:
                TotalSalesByCustomer();
                break;

            case 6:
                SalesWithAppliedDiscount();
                break;
        }

    }

    private void SalesWithAppliedDiscount() throws IOException {
       String jsonContent = saleService.findBaseInfoForSales();

       writeToFile(Path.of(OUTPUT_PATH + FILE_NAME_SALES_INFO), jsonContent);
    }

    private void TotalSalesByCustomer() throws IOException {
        String jsonContent = saleService.findInfoAboutCustomersAndTheirSales();

        writeToFile(Path.of(OUTPUT_PATH + FILE_NAME_CUSTOMERS_INFO), jsonContent);
    }

    private void carsWithTheirListOfParts() throws IOException {
        String jsonContent = carService.getAllCarWithListOfParts();

        writeToFile(Path.of(OUTPUT_PATH + FILE_NAME_CARS_AND_PARTS), jsonContent);
    }

    private void localSuppliers() throws IOException {

        String jsonContent = supplierService.getAllNotImporterSupplier();

        writeToFile(Path.of(OUTPUT_PATH + FILE_NAME_LOCAL_SUPPLIER), jsonContent);
    }

    private void carsFromMakeToyota() throws IOException {
        String jsonContent = carService.getAllToyotaCarOrderByModelAndTravelledDistance();

        writeToFile(Path.of(OUTPUT_PATH + FILE_NAME_TOYOTA), jsonContent);
    }

    private void orderedCustomers() throws IOException {
        String json = customerService.findAllOrderByBirthDateThanByExperience();

        writeToFile(Path.of(OUTPUT_PATH + FILE_NAME), json);
    }

    private void writeToFile(Path output, String json) throws IOException {
        Files.write(output, Collections.singleton(json));
    }

    private void seedDataToDB() throws IOException {
        supplierService.seedData();
        partService.seedData();
        carService.seedData();
        customerService.seedData();
        saleService.seedData();
    }
}
