package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.CustomerSeedDto;
import exam.model.entity.Customer;
import exam.repository.CustomerRepository;
import exam.service.CustomerService;
import exam.service.TownService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    public static final String CUSTOMER_INPUT_PATH = "src/main/resources/files/json/customers.json";
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    private final TownService townService;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, TownService townService) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of(CUSTOMER_INPUT_PATH));
    }

    @Override
    public String importCustomers() throws IOException {
        CustomerSeedDto[] customerSeedDto = gson.fromJson(readCustomersFileContent(), CustomerSeedDto[].class);

        StringBuilder sb = new StringBuilder();

       Arrays.stream(customerSeedDto)
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isExistCustomerEmail(dto.getEmail());

                    if (isValid) {
                        sb.append(String.format("Successfully imported Customer %s %s - %s",
                                dto.getFirstName(), dto.getLastName(), dto.getEmail()));
                    } else {
                        sb.append("Invalid Customer");
                    }

                    sb.append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Customer customer = modelMapper.map(dto, Customer.class);

                    customer.setTown(townService.getTownByName(dto.getTown().getName()));

                    return customer;
                }).forEach(customerRepository::save);


        return sb.toString().trim();
    }

    private boolean isExistCustomerEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
}
