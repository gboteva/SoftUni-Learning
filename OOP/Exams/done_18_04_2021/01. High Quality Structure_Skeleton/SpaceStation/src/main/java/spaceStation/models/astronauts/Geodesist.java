package spaceStation.models.astronauts;

import spaceStation.models.astronauts.BaseAstronaut;

public class Geodesist extends BaseAstronaut {
    private final static double INITIAL_UNIT_OXYGEN = 50;

    public Geodesist(String name) {
        super(name, INITIAL_UNIT_OXYGEN);
    }
}
