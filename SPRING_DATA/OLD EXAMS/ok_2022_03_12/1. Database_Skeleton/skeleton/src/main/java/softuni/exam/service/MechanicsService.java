package softuni.exam.service;


import softuni.exam.models.entity.Mechanic;

import java.io.IOException;

// TODO: Implement all methods
public interface MechanicsService {

    boolean areImported();

    String readMechanicsFromFile() throws IOException;

    String importMechanics() throws IOException;

    boolean existByFirstName(String firstName);

    Mechanic getByFirstName(String firstName);
}
