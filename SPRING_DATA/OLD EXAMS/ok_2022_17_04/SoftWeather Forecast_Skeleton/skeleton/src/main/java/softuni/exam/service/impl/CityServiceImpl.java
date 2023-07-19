package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CitySeedDTO;
import softuni.exam.models.entity.City;
import softuni.exam.repository.CityRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {
    public static final String CITIES_INPUT_PATH = "src/main/resources/files/json/cities.json";
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final CountryService countryService;

    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, CountryService countryService) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.countryService = countryService;
    }

    @Override
    public boolean areImported() {
        return cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(Path.of(CITIES_INPUT_PATH));
    }

    @Override
    public String importCities() throws IOException {

        CitySeedDTO[] citySeedDTOS = gson.fromJson(readCitiesFileContent(), CitySeedDTO[].class);

        StringBuilder sb = new StringBuilder();

       Arrays.stream(citySeedDTOS)
                .filter(citySeedDTO -> {
                    boolean isValid = validationUtil.isValid(citySeedDTO)
                            && !isExistInDB(citySeedDTO.getCityName());

                    if (isValid) {
                        sb.append(String.format("Successfully imported city %s - %d",
                                citySeedDTO.getCityName(), citySeedDTO.getPopulation()));
                    } else {
                        sb.append("Invalid city");
                    }

                    sb.append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    City city = modelMapper.map(dto, City.class);
                    city.setCountry(countryService.findById(dto.getCountry()));
                    return city;
                })
                .forEach(cityRepository::save);

        return sb.toString().trim();
    }

    @Override
    public City findById(Long city) {

        return cityRepository.findById(city).orElse(null);
    }

    private boolean isExistInDB(String cityName) {
        return cityRepository.existsByCityName(cityName);
    }
}
