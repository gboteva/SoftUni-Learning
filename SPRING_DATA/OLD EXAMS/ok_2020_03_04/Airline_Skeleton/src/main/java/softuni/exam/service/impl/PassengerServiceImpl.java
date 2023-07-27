package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PassengerSeedDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {
    public static final String PASSENGER_INPUT_PATH = "src/main/resources/files/json/passengers.json";
    private final PassengerRepository passengerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final TownService townService;

    public PassengerServiceImpl(PassengerRepository passengerRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, TownService townService) {
        this.passengerRepository = passengerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files.readString(Path.of(PASSENGER_INPUT_PATH));
    }

    @Override
    public String importPassengers() throws IOException {
        PassengerSeedDto[] passengerSeedDtos = gson.fromJson(readPassengersFileContent(), PassengerSeedDto[].class);
        StringBuilder sb = new StringBuilder();

        Arrays.stream(passengerSeedDtos)
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && !isExistByEmail(dto.getEmail())
                            && townService.isExistByName(dto.getTown());

                    if (isValid) {
                        sb.append(String.format("Successfully imported Passenger %s - %s",
                                dto.getLastName(), dto.getEmail()));
                    } else {
                        sb.append("Invalid Passenger");
                    }

                    sb.append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Passenger passenger = modelMapper.map(dto, Passenger.class);
                    passenger.setTown(townService.getTownByName(dto.getTown()));

                    return passenger;
                })
                .forEach(passengerRepository::save);

        return sb.toString().trim();
    }

    public boolean isExistByEmail(String email) {
       return passengerRepository.existsByEmail(email);
    }

    @Override
    public Passenger getByEmail(String email) {
       return passengerRepository.findByEmail(email);
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        StringBuilder sb = new StringBuilder();

        passengerRepository.findAllOrderByTicketCountAndEmail()
                .forEach(sb::append);

        return sb.toString();

    }
}
