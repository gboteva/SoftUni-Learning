package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CarRootSeedDTO;
import softuni.exam.models.entity.Car;
import softuni.exam.repository.CarsRepository;
import softuni.exam.service.CarsService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarsServiceImpl implements CarsService {
    private static String CARS_FILE_PATH = "src/main/resources/files/xml/cars.xml";
    private final CarsRepository carsRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;

    public CarsServiceImpl(CarsRepository carsRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil) {
        this.carsRepository = carsRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return carsRepository.count() > 0;
    }

    @Override
    public String readCarsFromFile() throws IOException {
        return Files.readString(Path.of(CARS_FILE_PATH));
    }

    @Override
    public String importCars() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        CarRootSeedDTO carRootSeedDTO = xmlParser.fromFile(CARS_FILE_PATH, CarRootSeedDTO.class);

        carRootSeedDTO.getCars().stream()
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !existByPlateNumber(dto.getPlateNumber());

                    if (isValid) {
                        sb.append(String.format("Successfully imported car %s - %s",
                                dto.getCarMake(), dto.getCarModel()));
                    } else {
                        sb.append("Invalid car");
                    }

                    sb.append(System.lineSeparator());

                    return isValid;
                }).map(dto -> modelMapper.map(dto, Car.class))
                .forEach(carsRepository::save);

        return sb.toString().trim();
    }

    @Override
    public boolean existsById(Long id) {
                return carsRepository.existsById(id);
    }

    @Override
    public Car getById(Long id) {
            return carsRepository.findById(id).orElse(null);
    }

    private boolean existByPlateNumber(String plateNumber) {
        return  carsRepository.existsByPlateNumber(plateNumber);
    }
}
