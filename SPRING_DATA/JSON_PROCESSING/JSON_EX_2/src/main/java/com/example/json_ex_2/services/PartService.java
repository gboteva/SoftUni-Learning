package com.example.json_ex_2.services;

import com.example.json_ex_2.models.entities.Part;

import java.io.IOException;
import java.util.List;

public interface PartService {
    void seedData() throws IOException;

    List<Part> getRandomParts();
}
