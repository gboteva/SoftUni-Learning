package com.example.xml_2;

import com.example.xml_2.models.DTO.carsWithParts.CarWithPartsRootDTO;
import com.example.xml_2.models.DTO.localSuppliers.LocalSupplierRootDTO;
import com.example.xml_2.models.DTO.orderedCustomer.OrderCustomerDTO;
import com.example.xml_2.models.DTO.orderedCustomer.OrderedCustomerRootDTO;
import com.example.xml_2.models.DTO.salesByCustomer.CustomerSalesRootDTO;
import com.example.xml_2.models.DTO.salesWithDiscount.SaleWithDiscountRootDTO;
import com.example.xml_2.models.DTO.seeds.CarRootSeedDTO;
import com.example.xml_2.models.DTO.seeds.CustomerRootSeedDTO;
import com.example.xml_2.models.DTO.seeds.PartRootSeedDTO;
import com.example.xml_2.models.DTO.seeds.SupplierRootSeedDTO;
import com.example.xml_2.models.DTO.toyotaCar.ToyotaCarRootDTO;
import com.example.xml_2.services.*;
import com.example.xml_2.util.XMLParser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Scanner;


@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    public static final String INPUT_PATH = "src/main/resources/input/";
    public static final String SUPPLIER_INPUT_FILE = "suppliers.xml";
    public static final String PART_INPUT_FILE = "parts.xml";
    public static final String CAR_INPUT_FILE = "cars.xml";
    public static final String CUSTOMER_INPUT_FILE = "customers.xml";

    public static final String OUT_PATH = "src/main/resources/output/";
    public static final String ORDERED_CUSTOMERS_FILE = "ordered-customers.xml";
    public static final String TOYOTA_CAR = "toyota-cars.xml";
    public static final String LOCAL_SUPPLIER = "local-suppliers.xml";
    public static final String CARS_WITH_PARTS = "cars-and-parts.xml";
    public static final String CUSTOMERS_WITH_SALES = "customers-total-sales.xml";
    public static final String SALES_WITH_DISCOUNT = "sales-discounts.xml";
    private final XMLParser xmlParser;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;

    private final SaleService saleService;

    private Scanner scanner;

    public CommandLineRunner(XMLParser xmlParser, SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.xmlParser = xmlParser;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        seedToDB();

        System.out.println("Enter the number of task: ");
        int number = Integer.parseInt(scanner.nextLine());

        switch (number){
            case 1 -> orderedCustomer();
            case 2 -> toyotaCars();
            case 3 -> localSuppliers();
            case 4 -> carsWithParts();
            case 5 -> totalSaleByCustomer();
            case 6 -> salesWithDiscount();
        }
    }

    private void salesWithDiscount() throws JAXBException {
        SaleWithDiscountRootDTO dto = saleService.getSalesWithDiscount();

        xmlParser.writeToFile(OUT_PATH + SALES_WITH_DISCOUNT, dto);
    }

    private void totalSaleByCustomer() throws JAXBException {
        CustomerSalesRootDTO dto = customerService.getCustomersWithSales();

        xmlParser.writeToFile(OUT_PATH + CUSTOMERS_WITH_SALES, dto);
    }

    private void carsWithParts() throws JAXBException {
        CarWithPartsRootDTO dto = carService.getCarWithParts();

        xmlParser.writeToFile(OUT_PATH + CARS_WITH_PARTS, dto);
    }

    private void localSuppliers() throws JAXBException {
        LocalSupplierRootDTO dto = supplierService.getLocalSuppliers();

        xmlParser.writeToFile(OUT_PATH + LOCAL_SUPPLIER, dto);
    }

    private void toyotaCars() throws JAXBException {
        ToyotaCarRootDTO dto = carService.getToyotaCarOrdered();

        xmlParser.writeToFile(OUT_PATH + TOYOTA_CAR, dto);
    }

    private void orderedCustomer() throws JAXBException {
        OrderedCustomerRootDTO orderedCustomer = customerService.getOrderCustomer();

        xmlParser.writeToFile(OUT_PATH + ORDERED_CUSTOMERS_FILE, orderedCustomer);
    }

    private void seedToDB() throws JAXBException, FileNotFoundException {
        if (supplierService.getCount() == 0){
            SupplierRootSeedDTO supplierRootSeedDTO =
                    xmlParser.fromFile(INPUT_PATH + SUPPLIER_INPUT_FILE, SupplierRootSeedDTO.class);

            supplierService.seedData(supplierRootSeedDTO);
        }

        if (partService.getCount() == 0){
            PartRootSeedDTO partRootSeedDTO =
                    xmlParser.fromFile(INPUT_PATH + PART_INPUT_FILE, PartRootSeedDTO.class);

            partService.seedData(partRootSeedDTO);
        }

        if (carService.getCount() == 0){
            CarRootSeedDTO carRootSeedDTO =
                    xmlParser.fromFile(INPUT_PATH + CAR_INPUT_FILE, CarRootSeedDTO.class);

            carService.seedData(carRootSeedDTO);
        }

        if (customerService.getCount() == 0){
            CustomerRootSeedDTO customerRootSeedDTO =
                    xmlParser.fromFile(INPUT_PATH + CUSTOMER_INPUT_FILE, CustomerRootSeedDTO.class);

            customerService.seedData(customerRootSeedDTO);
        }

        if (saleService.getCount() == 0){
            saleService.seedData();
        }

    }
}
