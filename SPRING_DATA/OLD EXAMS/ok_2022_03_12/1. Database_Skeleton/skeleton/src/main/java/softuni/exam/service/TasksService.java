package softuni.exam.service;

import softuni.exam.models.entity.Task;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

// TODO: Implement all methods
public interface TasksService {

    boolean areImported();

    String readTasksFileContent() throws IOException;

    String importTasks() throws IOException, JAXBException;

    String getCoupeCarTasksOrderByPrice();


}
