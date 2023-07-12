package com.example.xml_2.services;

import com.example.xml_2.models.DTO.salesWithDiscount.SaleWithDiscountDTO;
import com.example.xml_2.models.DTO.salesWithDiscount.SaleWithDiscountRootDTO;
import com.example.xml_2.models.entities.Car;
import com.example.xml_2.models.entities.Customer;
import com.example.xml_2.models.entities.Part;
import com.example.xml_2.models.entities.Sale;
import com.example.xml_2.repositories.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService{

    private final SaleRepository saleRepository;
    private final CarService carService;

    private final CustomerService customerService;

    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, CarService carService, CustomerService customerService, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @Override
    public long getCount() {
        return saleRepository.count();
    }

    @Override
    public void seedData() {
        List<Sale> sales = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Car car = carService.getRandomCar();
            Customer customer = customerService.getRandomCustomer();

            double[] discountNumbers = {0, 5, 10, 15, 20, 30, 40, 50};
            int randomIndex = ThreadLocalRandom.current().nextInt(0, 7 + 1);

            double discount = discountNumbers[randomIndex];

            if (customer.isYoungDriver()) {
                discount = discount + 5;
            }

           sales.add(new Sale(car, customer, discount));
        }

        saleRepository.saveAll(sales);

    }

    @Override
    public SaleWithDiscountRootDTO getSalesWithDiscount() {
        List<Sale> sales = saleRepository.findAll();

        List<SaleWithDiscountDTO> dtos = sales.stream()
                .map(sale -> {
                    SaleWithDiscountDTO dto = modelMapper.map(sale, SaleWithDiscountDTO.class);

                    dto.setDiscount(sale.getDiscount()/100);

                    BigDecimal price = BigDecimal.valueOf(0);

                    for (Part part : sale.getCar().getParts()) {
                        price = price.add(part.getPrice());
                    }

                    dto.setPrice(price.setScale(2, RoundingMode.HALF_UP));

                    double discount = dto.getDiscount() / 100;
                    BigDecimal divider = BigDecimal.valueOf(discount + 1);

                    BigDecimal withDiscount = (price.divide(divider, RoundingMode.HALF_EVEN));

                    dto.setPriceWithDiscount(withDiscount.setScale(2, RoundingMode.HALF_UP));

                    return dto;
                })
                .collect(Collectors.toList());

        SaleWithDiscountRootDTO rootDto = new SaleWithDiscountRootDTO();
        rootDto.setSales(dtos);

        return rootDto;

    }
}
