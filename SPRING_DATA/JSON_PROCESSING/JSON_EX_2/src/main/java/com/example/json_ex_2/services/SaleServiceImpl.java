package com.example.json_ex_2.services;

import com.example.json_ex_2.models.DTO.CarBaseInfoDTO;
import com.example.json_ex_2.models.DTO.CustomersWithCountOfCarsAndMoneyDTO;
import com.example.json_ex_2.models.DTO.SaleBaseInfo;
import com.example.json_ex_2.models.entities.Car;
import com.example.json_ex_2.models.entities.Customer;
import com.example.json_ex_2.models.entities.Part;
import com.example.json_ex_2.models.entities.Sale;
import com.example.json_ex_2.repositories.SaleRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    private final CarService carService;
    private final CustomerService customerService;

    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper, Gson gson, CarService carService, CustomerService customerService) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    public void seedData() {
        if (saleRepository.count() > 0) {
            return;
        }

        for (int i = 0; i < 20; i++) {
            double[] discountNumbers = {0, 5, 10, 15, 20, 30, 40, 50};
            int randomIndex = ThreadLocalRandom.current().nextInt(0, 7 + 1);

            double discount = discountNumbers[randomIndex];
            Customer randomCustomer = customerService.getRandomCustomer();

            if (randomCustomer.isYoungDriver()) {
                discount = discount + 5;
            }

            Sale sale = new Sale(carService.getRandomCar(), randomCustomer, discount);

            saleRepository.save(sale);
        }

    }

    @Override
    public String findInfoAboutCustomersAndTheirSales() {
        List<String> info = saleRepository.findAllInfoAboutCustomersWithSales();

        List<CustomersWithCountOfCarsAndMoneyDTO> list = new ArrayList<>();

        for (String row : info) {
            String[] token = row.split(",");
            String fullName = token[0];
            BigDecimal spentMoney = BigDecimal.valueOf(Double.parseDouble(token[1]));
            int boughtCar = Integer.parseInt(token[2]);
            double discount = Double.parseDouble(token[3]);

            spentMoney = spentMoney
                    .subtract(spentMoney.multiply(BigDecimal.valueOf(discount))
                            .divide(BigDecimal.valueOf(100), RoundingMode.DOWN));

            CustomersWithCountOfCarsAndMoneyDTO customer = new CustomersWithCountOfCarsAndMoneyDTO();
            customer.setFullName(fullName);
            customer.setSpentMoney(spentMoney.setScale(2, RoundingMode.DOWN));
            customer.setBoughtCars(boughtCar);
            list.add(customer);
        }

        return gson.toJson(list);

    }

    @Override
    public String findBaseInfoForSales() {
        List<Sale> allSales = saleRepository.findAll();

        List<SaleBaseInfo> sales = new ArrayList<>();

        for (Sale sale : allSales) {
            Car car = sale.getCar();
            Customer customer = sale.getCustomer();
            BigDecimal price = car.getParts().stream().map(Part::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));
            double discount = sale.getDiscount();
            BigDecimal priceWithDiscount = price
                    .subtract(price.multiply(BigDecimal.valueOf(discount))
                            .divide(BigDecimal.valueOf(100), RoundingMode.DOWN));
            CarBaseInfoDTO carBaseInfoDTO = new CarBaseInfoDTO();
            carBaseInfoDTO.setMake(car.getMake());
            carBaseInfoDTO.setModel(car.getModel());
            carBaseInfoDTO.setTravelledDistance(car.getTravelledDistance());

            SaleBaseInfo saleBaseInfo = new SaleBaseInfo();
            saleBaseInfo.setCarBaseInfo(carBaseInfoDTO);
            saleBaseInfo.setCustomerName(customer.getName());
            saleBaseInfo.setDiscount(discount);
            saleBaseInfo.setPrice(price.setScale(2, RoundingMode.DOWN));
            saleBaseInfo.setPriceWithDiscount(priceWithDiscount.setScale(2, RoundingMode.DOWN));

            sales.add(saleBaseInfo);
        }

        return gson.toJson(sales);
    }


}
