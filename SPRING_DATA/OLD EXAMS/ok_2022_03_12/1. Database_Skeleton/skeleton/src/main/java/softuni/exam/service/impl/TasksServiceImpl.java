package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TaskRootSeedDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;
import softuni.exam.models.entity.Task;
import softuni.exam.models.entity.enums.CarType;
import softuni.exam.repository.TasksRepository;
import softuni.exam.service.CarsService;
import softuni.exam.service.MechanicsService;
import softuni.exam.service.PartsService;
import softuni.exam.service.TasksService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class TasksServiceImpl implements TasksService {
    private final TasksRepository tasksRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private static String TASKS_FILE_PATH = "src/main/resources/files/xml/tasks.xml";

    private final MechanicsService mechanicsService;
    private final CarsService carsService;

    private final PartsService partsService;

    public TasksServiceImpl(TasksRepository tasksRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, MechanicsService mechanicsService, CarsService carsService, PartsService partsService) {
        this.tasksRepository = tasksRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mechanicsService = mechanicsService;
        this.carsService = carsService;
        this.partsService = partsService;
    }

    @Override
    public boolean areImported() {
        return tasksRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        File file = new File(TASKS_FILE_PATH);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();

        String line = reader.readLine();

        while(line != null) {
            sb.append(line).append("\n");

            line = reader.readLine();
        }

        return sb.toString().trim();
       // return Files.readString(Path.of(TASKS_FILE_PATH));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        TaskRootSeedDto taskRootSeedDto = xmlParser.fromFile(TASKS_FILE_PATH, TaskRootSeedDto.class);

        taskRootSeedDto.getTasks().stream()
                .filter(dto -> {
                    boolean isValid = validationUtil.isValid(dto)
                            && existMechanicWithName(dto.getMechanic().getFirstName())
                            && existCarById(dto.getCar().getId());
                    if (isValid) {
                        DecimalFormat formatter = new DecimalFormat("####################.00");
                        formatter.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));

                        sb.append(String.format("Successfully imported task %s", formatter.format(dto.getPrice())));
                    } else {
                        sb.append("Invalid task");
                    }

                    sb.append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Task task = modelMapper.map(dto, Task.class);
                    Car car = carsService.getById(dto.getCar().getId());
                    task.setCar(car);

                    Mechanic mechanic = mechanicsService.getByFirstName(dto.getMechanic().getFirstName());
                    task.setMechanic(mechanic);

                    Part part = partsService.getById(dto.getPart().getId());
                    task.setPart(part);

                    LocalDateTime dateTime = LocalDateTime.parse(dto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    //task.setDate(dateTime.toLocalDate());
                    return task;

                } )
                .forEach(tasksRepository::save);

        return sb.toString().trim();
    }

    private boolean existCarById(Long id) {
        return carsService.existsById(id);
    }

    private boolean existMechanicWithName(String firstName) {
        return mechanicsService.existByFirstName(firstName);
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        StringBuilder sb = new StringBuilder();
        List<Task> tasks = tasksRepository.findAllTasksWithCoupeCar(CarType.coupe);
        tasks.forEach(sb::append);
        return sb.toString();
    }
}
