package com.example.xml_2.services;

import com.example.xml_2.models.DTO.seeds.PartRootSeedDTO;
import com.example.xml_2.models.entities.Part;
import com.example.xml_2.repositories.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final SupplierService supplierService;

    public PartServiceImpl(PartRepository partRepository, ModelMapper modelMapper, SupplierService supplierService) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.supplierService = supplierService;
    }

    @Override
    public long getCount() {
        return partRepository.count();
    }

    @Override
    public void seedData(PartRootSeedDTO partRootSeedDTO) {
        partRootSeedDTO.getParts()
                .stream()
                .map(partSeedDTO ->{
                  Part part =  modelMapper.map(partSeedDTO, Part.class);
                  part.setSupplier(supplierService.getRandomSupplier());
                  return part;
                })
                .forEach(partRepository::save);
    }

    @Override
    public List<Part> getRandomParts() {
        int randomPartsCount = ThreadLocalRandom.current().nextInt(10, 21);
        long countIds = partRepository.count();

        List<Part> parts = new ArrayList<>();

        for (int i = 0; i <= randomPartsCount; i++) {
            long randomId = ThreadLocalRandom.current().nextLong(1, partRepository.count());
            parts.add(partRepository.findById(randomId).orElse(null));
        }

        return parts;

    }
}
