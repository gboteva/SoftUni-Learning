package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ApartmentSeedRootDto;
import softuni.exam.models.entity.Apartment;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    public static final String APARTMENT_FILE_PATH = "src/main/resources/files/xml/apartments.xml";
    private final ApartmentRepository apartmentRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    private final TownService townService;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, TownService townService) {
        this.apartmentRepository = apartmentRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(Path.of(APARTMENT_FILE_PATH));
    }

    @Override
    public String importApartments() throws IOException, JAXBException {
        ApartmentSeedRootDto rootDto = xmlParser.fromFile(APARTMENT_FILE_PATH, ApartmentSeedRootDto.class);

        StringBuilder sb = new StringBuilder();

        rootDto.getApartments().stream()
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && isValidTownAndArea(dto.getTown(), dto.getArea());
                    if (isValid) {
                        sb.append(String.format("Successfully imported apartment %s - %.2f",
                                dto.getApartmentType().toString(), dto.getArea()));
                    } else {
                        sb.append("Invalid apartment");
                    }

                    sb.append(System.lineSeparator());

                    return isValid;
                }).map(seedDto -> {
                    Apartment apartment = modelMapper.map(seedDto, Apartment.class);
                    apartment.setTown(townService.findByName(seedDto.getTown()));

                    return apartment;
                })
                .forEach(apartmentRepository::save);

        return sb.toString().trim();
    }

    @Override
    public Apartment findById(Long id) {
        return apartmentRepository.findById(id).orElse(null);
    }

    private boolean isValidTownAndArea(String townName, double area) {

        List<Apartment> apartmentsInDB = apartmentRepository.findAllByArea(area);

        for (Apartment apartment : apartmentsInDB) {

            if (apartment.getTown().getTownName().equals(townName)
            && apartment.getArea() == area){
                return false;
            }
        }

        return true;
    }

    private boolean isValidTown(String town) {
       return townService.findByName(town) !=null;
    }


}
