package aquarium.core;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;
import aquarium.repositories.Repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{

    private DecorationRepository decorations;
    private Map<String, Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new LinkedHashMap<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium;

        if  (aquariumType.equals("FreshwaterAquarium")){
            aquarium = new FreshwaterAquarium(aquariumName);
        }else if (aquariumType.equals("SaltwaterAquarium")){
            aquarium = new SaltwaterAquarium(aquariumName);
        }else {
            throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }
        this.aquariums.put(aquariumName, aquarium);
        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;
        if (type.equals("Ornament")){
            decoration = new Ornament();
        }else if (type.equals("Plant")){
            decoration = new Plant();
        }else {
            throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }

        this.decorations.add(decoration);

        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
       Aquarium aquarium = aquariums.get(aquariumName);

       Decoration decoration = this.decorations.findByType(decorationType);
       if (decoration == null){
           throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));
       }

       aquarium.addDecoration(decoration);
       this.decorations.remove(decoration);
       return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
       Aquarium aquarium = this.aquariums.get(aquariumName);

       Fish fish;
        if (fishType.equals("FreshwaterFish")){
            fish = new FreshwaterFish(fishName, fishSpecies, price);
        }else if (fishType.equals("SaltwaterFish")){
            fish = new SaltwaterFish(fishName, fishSpecies, price);
        }else {
            throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }

        boolean isValidFreshWatter = aquarium.getClass().getSimpleName().equals("FreshwaterAquarium") && fishType.equals("FreshwaterFish");
        boolean isValidSaltWatter = aquarium.getClass().getSimpleName().equals("SaltwaterAquarium") && fishType.equals("SaltwaterFish");

       if (isValidFreshWatter || isValidSaltWatter){
           aquarium.addFish(fish);
       }else{
           throw new IllegalArgumentException(WATER_NOT_SUITABLE);
       }

       return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = this.aquariums.get(aquariumName);
        aquarium.feed();
        return String.format(FISH_FED, aquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium aquarium = this.aquariums.get(aquariumName);
        double fishTotalPrice = aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();
        double decorationsTotalPrice = aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
        double total = fishTotalPrice + decorationsTotalPrice;
        return String.format(VALUE_AQUARIUM, aquariumName, total);
    }

    @Override
    public String report() {
        StringBuilder builder = new StringBuilder();
        for (Aquarium aquarium : this.aquariums.values()) {
            builder.append(aquarium.getInfo()).append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
