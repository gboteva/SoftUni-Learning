package com.example.xml_2.services;

import com.example.xml_2.models.DTO.localSuppliers.LocalSupplier;
import com.example.xml_2.models.DTO.localSuppliers.LocalSupplierRootDTO;
import com.example.xml_2.models.DTO.seeds.SupplierRootSeedDTO;
import com.example.xml_2.models.entities.Supplier;
import com.example.xml_2.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SupplierServiceImpl implements SupplierService {


    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public long getCount() {
        return this.supplierRepository.count();
    }

    @Override
    public void seedData(SupplierRootSeedDTO supplierRootSeedDTO)  {
        supplierRootSeedDTO.getSuppliers().stream()
                .map(supplierSeedDTO -> modelMapper.map(supplierSeedDTO, Supplier.class))
                .forEach(supplierRepository::save);
    }

    @Override
    public Supplier getRandomSupplier() {
        long countId = supplierRepository.count();
        long randomId = ThreadLocalRandom.current().nextLong(1, countId + 1);

        return supplierRepository.findById(randomId).orElse(null);
    }

    @Override
    public LocalSupplierRootDTO getLocalSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAllByIsImporterFalse();

        List<LocalSupplier> collect = suppliers.stream()
                .map(supplier -> {
                    LocalSupplier dto = modelMapper.map(supplier, LocalSupplier.class);
                    dto.setPartsCount(supplier.getParts().size());
                    return dto;
                }).collect(Collectors.toList());

        LocalSupplierRootDTO localSupplierRootDTO = new LocalSupplierRootDTO();

        localSupplierRootDTO.setSuppliers(collect);

        return localSupplierRootDTO;
    }
}
