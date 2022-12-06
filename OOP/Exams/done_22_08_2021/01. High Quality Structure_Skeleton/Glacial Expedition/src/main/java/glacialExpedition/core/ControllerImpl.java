package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.Repository;
import glacialExpedition.repositories.StateRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int countExploredStates;

    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer = null;

        if (type.equals("NaturalExplorer")){
            explorer = new NaturalExplorer(explorerName);
        }else if (type.equals("GlacierExplorer")){
            explorer = new GlacierExplorer(explorerName);
        }else if (type.equals("AnimalExplorer")){
            explorer = new AnimalExplorer(explorerName);
        }else {
            throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }

        this.explorerRepository.add(explorer);

        return String.format(EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        state.getExhibits().addAll(Arrays.asList(exhibits));
        this.stateRepository.add(state);

        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        if ( explorer == null){
            throw new IllegalArgumentException(String.format(EXPLORER_DOES_NOT_EXIST, explorerName));
        }

        this.explorerRepository.remove(explorer);
        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        List<Explorer> mostSuitableExplorers = explorerRepository.getCollection().stream()
                .filter(explorer -> explorer.getEnergy() > 50)
                .collect(Collectors.toList());

        if (mostSuitableExplorers.size() == 0){
            throw new IllegalArgumentException(STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        State state = stateRepository.byName(stateName);
        Mission mission = new MissionImpl();
        mission.explore(state, mostSuitableExplorers);

        long countRetired = mostSuitableExplorers.stream().filter(e -> e.getEnergy() == 0).count();

        mostSuitableExplorers.stream().filter(e->e.getEnergy() == 0)
                .forEach(e->retireExplorer(e.getName()));

        countExploredStates++;
        return String.format(STATE_EXPLORER, stateName, countRetired);
    }

    @Override
    public String finalResult() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(FINAL_STATE_EXPLORED, countExploredStates)).append(System.lineSeparator());
        builder.append(FINAL_EXPLORER_INFO).append(System.lineSeparator());

        for (Explorer explorer : explorerRepository.getCollection()) {
            builder.append(String.format(FINAL_EXPLORER_NAME, explorer.getName())) .append(System.lineSeparator());
            builder.append(String.format(FINAL_EXPLORER_ENERGY, explorer.getEnergy())).append(System.lineSeparator());
            String exhibits;
            if (explorer.getSuitcase().getExhibits().size() == 0){
                exhibits = "None";
            }else {
                exhibits = explorer.getSuitcase().getExhibits().stream().collect(Collectors.joining(FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER));
            }

            builder.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, exhibits)).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
