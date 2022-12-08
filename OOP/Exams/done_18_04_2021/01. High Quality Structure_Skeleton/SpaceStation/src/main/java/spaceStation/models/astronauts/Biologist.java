package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{
    private final static double INITIAL_UNIT_OXYGEN = 70;

    public Biologist(String name) {
        super(name, INITIAL_UNIT_OXYGEN);
    }

    @Override
    public void breath() {
        super.setOxygen(Math.max(0, (super.getOxygen() - 5)));
    }
}
