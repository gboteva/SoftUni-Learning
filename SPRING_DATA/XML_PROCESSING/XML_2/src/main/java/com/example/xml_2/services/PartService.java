package com.example.xml_2.services;

import com.example.xml_2.models.DTO.seeds.PartRootSeedDTO;
import com.example.xml_2.models.entities.Part;

import java.util.List;

public interface PartService {
    long getCount();

    void seedData(PartRootSeedDTO partRootSeedDTO);

    List<Part> getRandomParts();
}
