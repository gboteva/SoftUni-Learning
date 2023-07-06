package com.example.json_ex_2.services;

import com.example.json_ex_2.models.DTO.PartSeedDTO;
import com.example.json_ex_2.models.entities.Part;
import com.example.json_ex_2.repositories.PartRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService {
    public static final String INPUT_PATH = "src/main/resources/input/parts.json";
    private final PartRepository partRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    private final SupplierService supplierService;

    public PartServiceImpl(PartRepository partRepository, Gson gson, ModelMapper modelMapper, SupplierService supplierService) {
        this.partRepository = partRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.supplierService = supplierService;
    }

    @Override
    public void seedData() throws IOException {
        if (partRepository.count() > 0){
            return;
        }

        String jsonContent = Files.readString(Path.of(INPUT_PATH));

        PartSeedDTO[] partSeedDTOS = gson.fromJson(jsonContent, PartSeedDTO[].class);

        Arrays.stream(partSeedDTOS)
                .map(dto -> {
                    Part part = modelMapper.map(dto, Part.class);
                    part.setSupplier(supplierService.getRandomSupplier());
                    return part;
                })
                .forEach(partRepository::save);

    }

    @Override
    public List<Part> getRandomParts() {
        int randomCount = ThreadLocalRandom.current().nextInt(3, 6);
        long countOfRepo = partRepository.count();

        List<Part> parts = new ArrayList<>();

        for (int i = 0; i <= randomCount; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, countOfRepo + 1);
            parts.add(partRepository.findById(randomId).orElse(null));
        }

        return parts;
    }
}
