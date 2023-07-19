package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.LaptopSeedDto;
import exam.model.entity.Laptop;
import exam.repository.LaptopRepository;
import exam.service.LaptopService;
import exam.service.ShopService;
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
public class LaptopServiceImpl implements LaptopService {
    public static final String LAPTOP_INPUT_FILE_PATH = "src/main/resources/files/json/laptops.json";
    private final LaptopRepository laptopRepository;
    private final ModelMapper modelMapper;
    private  final ValidationUtil validationUtil;
    private final Gson gson;
    private final ShopService shopService;

    public LaptopServiceImpl(LaptopRepository laptopRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, ShopService shopService) {
        this.laptopRepository = laptopRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.shopService = shopService;
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(Path.of(LAPTOP_INPUT_FILE_PATH));
    }

    @Override
    public String importLaptops() throws IOException {
        LaptopSeedDto[] laptopSeedDto = gson.fromJson(readLaptopsFileContent(), LaptopSeedDto[].class);

        StringBuilder sb = new StringBuilder();

        Arrays.stream(laptopSeedDto)
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isExistMacAddress(dto.getMacAddress());

                    if (isValid) {
                        sb.append(String.format("Successfully imported Laptop %s - %.2f - %d - %d",
                                dto.getMacAddress(), dto.getCpuSpeed(), dto.getRam(), dto.getStorage()));
                    } else {
                        sb.append("Invalid Laptop");
                    }

                    sb.append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Laptop laptop = modelMapper.map(dto, Laptop.class);
                    laptop.setShop(shopService.getShopByName(dto.getShop().getName()));
                    return laptop;
                }).forEach(laptopRepository::save);

        return sb.toString().trim();
    }

    private boolean isExistMacAddress(String macAddress) {
        return laptopRepository.existsByMacAddress(macAddress);
    }

    @Override
    public String exportBestLaptops() {
        List<Laptop> bestLaptops = laptopRepository.findAllOrdered();

        StringBuilder sb = new StringBuilder();

        bestLaptops
                .forEach(sb::append);

        return sb.toString();
    }
}
