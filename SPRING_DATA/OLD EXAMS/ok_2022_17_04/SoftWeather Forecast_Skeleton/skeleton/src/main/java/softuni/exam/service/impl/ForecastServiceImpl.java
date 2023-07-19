package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastSeedRootDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.enums.DayOfWeek;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.CityService;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XMLParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForecastServiceImpl implements ForecastService {
    public static final String FORECAST_INPUT_FILE_PATH = "src/main/resources/files/xml/forecasts.xml";
    private final ForecastRepository forecastRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XMLParser xmlParser;
    private final CityService cityService;

    public ForecastServiceImpl(ForecastRepository forecastRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XMLParser xmlParser, CityService cityService) {
        this.forecastRepository = forecastRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.cityService = cityService;
    }

    @Override
    public boolean areImported() {
        return forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(FORECAST_INPUT_FILE_PATH));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {

        ForecastSeedRootDTO rootDTO = xmlParser.fromFile(FORECAST_INPUT_FILE_PATH, ForecastSeedRootDTO.class);

        StringBuilder sb = new StringBuilder();

        rootDTO.getForecasts().stream()
                .filter(forecastDTO -> {
                    boolean isValid = validationUtil.isValid(forecastDTO)
                            && !IsExistDayOfWeekOnCity(forecastDTO.getDayOfWeek(), forecastDTO.getCity());


                    if (isValid) {
                        sb.append(String.format("Successfully import forecast %s - %.2f", forecastDTO.getDayOfWeek(),
                                forecastDTO.getMaxTemperature()));
                    } else {
                        sb.append("Invalid forecast");
                    }

                    sb.append(System.lineSeparator());

                    return isValid;
                })
                .map(forecastSeedDTO ->{
                    Forecast forecast = modelMapper.map(forecastSeedDTO, Forecast.class);
                   forecast.setCity(cityService.findById(forecastSeedDTO.getCity()));
                   return forecast;
                })
                .forEach(forecastRepository::save);


        return sb.toString().trim();
    }

    private boolean IsExistDayOfWeekOnCity(DayOfWeek dayOfWeek, Long cityId ) {
       City city = cityService.findById(cityId);

       return forecastRepository.existsByDayOfWeekAndCity(dayOfWeek, city);
    }

    @Override
    public String exportForecasts() {
        List<Forecast> forecasts = forecastRepository.findAllByDayOfWeekAndCityPopulationLessThan(DayOfWeek.SUNDAY, 150000);

        StringBuilder sb = new StringBuilder();

        forecasts.forEach(forecast -> {
            sb.append(String.format("City: %s:\n" +
                    "\t\t-min temperature: %.2f\n" +
                    "\t\t--max temperature: %.2f\n" +
                    "\t\t---sunrise: %s\n" +
                    "\t\t----sunset: %s\n", forecast.getCity().getCityName(),
                    forecast.getMinTemperature(), forecast.getMaxTemperature(),
                    forecast.getSunrise().toString(), forecast.getSunset().toString()));
        });

        return sb.toString().trim();
    }
}
