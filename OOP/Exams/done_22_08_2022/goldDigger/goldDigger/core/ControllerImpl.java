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

public class ControllerImpl implements Controller{

    private Repository<Discoverer> discovererRepository;
    private Repository<Spot> spotRepository;
    private int inspectedSpotCount;

    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
        this.inspectedSpotCount = 0;
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
       Discoverer discoverer;
       switch (kind){
           case "Anthropologist":
               discoverer = new Anthropologist(discovererName);
               break;
           case "Archaeologist":
               discoverer = new Archaeologist(discovererName);
               break;
           case "Geologist":
               discoverer = new Geologist(discovererName);
               break;
           default: throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
       }

       this.discovererRepository.add(discoverer);
       return String.format(DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {

        Spot spot = new SpotImpl(spotName);

        spot.getExhibits().addAll(Arrays.asList(exhibits));

        spotRepository.add(spot);

        return String.format(SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discovererToExclude = discovererRepository.byName(discovererName);

        if (discovererToExclude == null){
            throw new IllegalArgumentException(String.format(DISCOVERER_DOES_NOT_EXIST, discovererName));
        }

        discovererRepository.remove(discovererToExclude);

        return String.format(DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
       Spot spotToInspect = spotRepository.byName(spotName);

        List<Discoverer> picked = this.discovererRepository.getCollection().stream()
                .filter(d->d.getEnergy() > 45)
                .collect(Collectors.toList());

        if (picked.isEmpty()){
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }

        Operation operation = new OperationImpl();
        operation.startOperation(spotToInspect, picked);

        inspectedSpotCount++;

        int excludedOfTheMission = picked.stream().filter(d->d.getEnergy() == 0)
                .collect(Collectors.toList()).size();

        return String.format(INSPECT_SPOT, spotName, excludedOfTheMission);

    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(FINAL_SPOT_INSPECT, inspectedSpotCount)).append(System.lineSeparator());
        builder.append(FINAL_DISCOVERER_INFO).append(System.lineSeparator());
        for (Discoverer discoverer : this.discovererRepository.getCollection()) {
            builder.append(String.format(FINAL_DISCOVERER_NAME, discoverer.getName())).append(System.lineSeparator());
            builder.append(String.format(FINAL_DISCOVERER_ENERGY, discoverer.getEnergy())).append(System.lineSeparator());
            String exhibitsToPrint;
            if (discoverer.getMuseum().getExhibits().isEmpty()){
                exhibitsToPrint = "None";
            }else {
                exhibitsToPrint = String.join(FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER,discoverer.getMuseum().getExhibits());
            }

            builder.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS, exhibitsToPrint)).append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
