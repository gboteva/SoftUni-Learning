package com.example.xml_2.services;

import com.example.xml_2.models.DTO.orderedCustomer.OrderCustomerDTO;
import com.example.xml_2.models.DTO.orderedCustomer.OrderedCustomerRootDTO;
import com.example.xml_2.models.DTO.salesByCustomer.CustomerSalesRootDTO;
import com.example.xml_2.models.DTO.salesByCustomer.CustomerWithSalesDTO;
import com.example.xml_2.models.DTO.seeds.CustomerRootSeedDTO;
import com.example.xml_2.models.entities.Customer;
import com.example.xml_2.models.entities.Part;
import com.example.xml_2.models.entities.Sale;
import com.example.xml_2.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public long getCount() {
        return customerRepository.count();
    }

    @Override
    public void seedData(CustomerRootSeedDTO customerRootSeedDTO) {
        customerRootSeedDTO.getCustomers()
                .stream()
                .map(customerSeedDTO -> {
                   Customer customer = modelMapper.map(customerSeedDTO, Customer.class);
                    LocalDateTime birthdate = LocalDateTime.parse(customerSeedDTO.getBirthdate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                    customer.setBirthDate(birthdate);
                    return customer;
                })
                .forEach(customerRepository::save);

    }

    @Override
    public Customer getRandomCustomer() {
        long count = customerRepository.count();

        long randomId = ThreadLocalRandom.current().nextLong(1, count + 1);

        return customerRepository.findById(randomId).orElse(null);

    }

    @Override
    public OrderedCustomerRootDTO getOrderCustomer() {
        List<Customer> customers = customerRepository.findAllOrderByBirthdateAndIsYoungDriver();

        List<OrderCustomerDTO> orderedCustomersDTO = customers.stream()
                .map(customer ->{
                   OrderCustomerDTO dto = modelMapper.map(customer, OrderCustomerDTO.class);
                   return dto;
                } )
                .collect(Collectors.toList());

        OrderedCustomerRootDTO orderedCustomerRootDTO = new OrderedCustomerRootDTO();
        orderedCustomerRootDTO.setCustomers(orderedCustomersDTO);

        return orderedCustomerRootDTO;
    }

    @Override
    public CustomerSalesRootDTO getCustomersWithSales() {
        List<Customer> customers = customerRepository.findAllCustomersWithSales();

        List<CustomerWithSalesDTO> dtos = customers.stream()
                .map(customer -> {
                    CustomerWithSalesDTO dto = modelMapper.map(customer, CustomerWithSalesDTO.class);
                    dto.setFullName(customer.getName());
                    dto.setBoughtCars(customer.getSales().size());

                    for (Sale sale : customer.getSales()) {
                        List<Part> parts = sale.getCar().getParts();
                        BigDecimal money = BigDecimal.valueOf(0);
                        for (Part part : parts) {
                           money = money.add(part.getPrice());
                        }
                          dto.setSpentMoney(money.setScale(2, RoundingMode.HALF_EVEN));
                    }

                    return dto;
                }).collect(Collectors.toList());

        CustomerSalesRootDTO rootDto = new CustomerSalesRootDTO();
        rootDto.setCustomers(dtos);

        return rootDto;
    }
}
