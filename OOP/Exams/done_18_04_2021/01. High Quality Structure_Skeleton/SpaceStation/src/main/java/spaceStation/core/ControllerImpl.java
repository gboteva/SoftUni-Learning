package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;
import spaceStation.repositories.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static spaceStation.core.common.ConstantMessages.*;
import static spaceStation.core.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private Repository<Astronaut> astronautRepository;
    private Repository<Planet> planetRepository;
    private int countExploredPlanets;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.countExploredPlanets = 0;
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        if (type.equals("Biologist")) {
            astronaut = new Biologist(astronautName);
        } else if (type.equals("Meteorologist")) {
            astronaut = new Meteorologist(astronautName);
        } else if (type.equals("Geodesist")) {
            astronaut = new Geodesist(astronautName);
        } else {
            throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }

        astronautRepository.add(astronaut);
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {

        Planet planet = new PlanetImpl(planetName);

        planet.getItems().addAll(Arrays.asList(items));

        planetRepository.add(planet);

        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronautToRetire = astronautRepository.findByName(astronautName);

        if (astronautToRetire == null) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }

        astronautRepository.remove(astronautToRetire);

        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        Planet planet = planetRepository.findByName(planetName);

        List<Astronaut> pickedAstronauts = astronautRepository.getModels().stream()
                .filter(a -> a.getOxygen() > 60)
                .collect(Collectors.toList());

        if (pickedAstronauts.size() == 0) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        Mission mission = new MissionImpl();

        mission.explore(planet, pickedAstronauts);

        int countDeadAstronauts = pickedAstronauts.stream()
                .filter(a -> !a.canBreath()).collect(Collectors.toList()).size();

        countExploredPlanets++;

        return String.format(PLANET_EXPLORED, planetName, countDeadAstronauts);

    }

    @Override
    public String report() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format(REPORT_PLANET_EXPLORED, countExploredPlanets)).append(System.lineSeparator());

        builder.append(REPORT_ASTRONAUT_INFO).append(System.lineSeparator());


        for (Astronaut astro : astronautRepository.getModels()) {
            builder.append(String.format(REPORT_ASTRONAUT_NAME, astro.getName())).append(System.lineSeparator());
            builder.append(String.format(REPORT_ASTRONAUT_OXYGEN, astro.getOxygen())).append(System.lineSeparator());

            if (astro.getBag().getItems().size() == 0) {
                builder.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, "none")).append(System.lineSeparator());
            } else {
                String items = String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, astro.getBag().getItems());
                builder.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, items)).append(System.lineSeparator());
            }
        }

        return builder.toString().trim();
    }
}
