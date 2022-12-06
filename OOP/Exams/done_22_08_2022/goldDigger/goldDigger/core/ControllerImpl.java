package goldDigger.core;

import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.Repository;
import goldDigger.repositories.SpotRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

   private Repository<Discoverer> discovererRepository;
    private Repository<Spot> spotRepository;
    private int countInspectedSpot;

    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
        this.countInspectedSpot = 0;
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {

        Discoverer discoverer;
        if (kind.equals("Archaeologist")) {
            discoverer = new Archaeologist(discovererName);
        } else if (kind.equals("Anthropologist")) {
            discoverer = new Anthropologist(discovererName);
        } else if (kind.equals("Geologist")) {
            discoverer = new Geologist(discovererName);
        } else {
            throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
        }
        this.discovererRepository.add(discoverer);
        return String.format(DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {

        List<String> exhibitsList = Arrays.asList(exhibits);
        Spot spot = new SpotImpl(spotName);
        exhibitsList.forEach(e -> spot.getExhibits().add(e));
        spotRepository.add(spot);

        return String.format(SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = discovererRepository.byName(discovererName);

        if (discoverer == null) {
            throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST, discovererName));
        }

        discovererRepository.remove(discoverer);
        return String.format(DISCOVERER_EXCLUDE, discovererName);

    }

    @Override
    public String inspectSpot(String spotName) {
        List<Discoverer> discoverers = discovererRepository.getCollection().stream()
                .filter(d -> d.getEnergy() > 45)
                .collect(Collectors.toList());

        if (discoverers.size() == 0) {
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }

        Spot spot = spotRepository.byName(spotName);

        Operation operation = new OperationImpl();
        operation.startOperation(spot, discoverers);
        this.countInspectedSpot++ ;

        //not working corect
        return String.format(INSPECT_SPOT, spotName, discovererRepository.getCollection().size() - discoverers.size());
    }

    @Override
    public String getStatistics() {
      StringBuilder builder = new StringBuilder();
      builder.append(this.countInspectedSpot).append(" spots were inspected.").append(System.lineSeparator());
      builder.append("Information for the discoverers:").append(System.lineSeparator());

        for (Discoverer discoverer : discovererRepository.getCollection()) {
            String name = discoverer.getName();
            double energy = discoverer.getEnergy();
            String museum;
            if (discoverer.getMuseum().getExhibits().isEmpty()){
                museum = "None";
            }else {
                museum = String.join(FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER, discoverer.getMuseum().getExhibits());
            }
            builder.append(String.format(FINAL_DISCOVERER_NAME, name)).append(System.lineSeparator());
            builder.append(String.format(FINAL_DISCOVERER_ENERGY, energy)).append(System.lineSeparator());
            builder.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS, museum)).append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
