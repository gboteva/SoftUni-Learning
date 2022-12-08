package spaceStation.models.astronauts;

import spaceStation.models.astronauts.BaseAstronaut;

public class Meteorologist extends BaseAstronaut {
    private final static double INITIAL_UNIT_OXYGEN = 90;

    public Meteorologist(String name) {
        super(name, INITIAL_UNIT_OXYGEN);
    }
}
