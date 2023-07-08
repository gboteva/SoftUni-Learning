package com.example.json_ex_2.services;

import com.example.json_ex_2.models.DTO.SupplierSeedDTO;
import com.example.json_ex_2.models.DTO.SupplierWithCountOfPartsNotImporterDTO;
import com.example.json_ex_2.models.entities.Supplier;
import com.example.json_ex_2.repositories.SupplierRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService{
    public static final String INPUT_PATH = "src/main/resources/input/suppliers.json";
    private final SupplierRepository supplierRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, Gson gson, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedData() throws IOException {
        if (supplierRepository.count() > 0){
            return;
        }

        String stringContent = Files.readString(Path.of(INPUT_PATH));

        SupplierSeedDTO[] supplierSeedDTOS = gson.fromJson(stringContent, SupplierSeedDTO[].class);

        Arrays.stream(supplierSeedDTOS)
                .map(dto -> modelMapper.map(dto, Supplier.class))
                .forEach(supplierRepository::save);

    }

    @Override
    public Supplier getRandomSupplier() {
        Long randomId = ThreadLocalRandom.current().nextLong(1, supplierRepository.count()+1);

        return supplierRepository.findById(randomId).orElse(null);
    }

    @Override
    public String getAllNotImporterSupplier() {
        List<Supplier> suppliersNotImporter = supplierRepository.findAllByImporterFalse();

        List<Object> suppliersDTOs = suppliersNotImporter.stream().map(supplier -> {
            SupplierWithCountOfPartsNotImporterDTO dto = modelMapper.map(supplier, SupplierWithCountOfPartsNotImporterDTO.class);
            dto.setCountOfParts(supplier.getParts().size());
            return dto;
        }).collect(Collectors.toList());

        return gson.toJson(suppliersDTOs);

    }
}
