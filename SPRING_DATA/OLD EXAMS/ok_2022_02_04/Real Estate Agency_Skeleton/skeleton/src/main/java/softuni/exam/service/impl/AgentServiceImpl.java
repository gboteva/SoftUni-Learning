package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentSeedDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.AgentRepository;
import softuni.exam.service.AgentService;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentServiceImpl implements AgentService {
    public static final String AGENT_INPUT_FILE = "src/main/resources/files/json/agents.json";
    private final AgentRepository agentRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    private final TownService townService;

    public AgentServiceImpl(AgentRepository agentRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, TownService townService) {
        this.agentRepository = agentRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(Path.of(AGENT_INPUT_FILE));
    }

    @Override
    public String importAgents() throws IOException {
        AgentSeedDTO[] agentSeedDTOS = gson.fromJson(readAgentsFromFile(), AgentSeedDTO[].class);

        StringBuilder sb = new StringBuilder();

        Arrays.stream(agentSeedDTOS).filter(agentDto -> {
                    boolean isValid = validationUtil.isValid(agentDto)
                            && !isExistAgent(agentDto.getFirstName(), agentDto.getEmail());

                    sb.append(isValid ? "Successfully imported agent - " + agentDto.getFirstName() + " " + agentDto.getLastName()
                            : "Invalid agent");

                    sb.append(System.lineSeparator());

                    return isValid;
                })
                .map(dto -> {
                    Agent agent = modelMapper.map(dto, Agent.class);
                    Town town = townService.findByName(dto.getTown());
                    agent.setTown(town);
                    return agent;
                })
                .forEach(agentRepository::save);

        return sb.toString().trim();
    }

    @Override
    public boolean isExistAgentByName(String agentName) {
       return agentRepository.existsByFirstName(agentName);
    }

    @Override
    public Agent findByName(String firstName) {
        return agentRepository.findByFirstName(firstName);
    }

    private boolean isExistAgent(String firstName, String email) {
        return  agentRepository.existsByFirstNameOrEmail(firstName, email);
    }
}
