package softuni.exam.service;


import softuni.exam.models.entity.Agent;
import softuni.exam.repository.AgentRepository;

import java.io.IOException;

public interface AgentService {

    boolean areImported();

    String readAgentsFromFile() throws IOException;
	
	String importAgents() throws IOException;

    boolean isExistAgentByName(String agentName);

    Agent findByName(String firstName);
}
