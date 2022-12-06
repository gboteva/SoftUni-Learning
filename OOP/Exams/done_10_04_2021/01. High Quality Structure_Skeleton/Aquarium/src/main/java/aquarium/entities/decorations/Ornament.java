package aquarium.entities.decorations;

public class Ornament extends BaseDecoration{
    private final static int COMFORT_VALUE = 1;
    private final static double PRICE_VALUE = 5;

    public Ornament() {
        super(COMFORT_VALUE, PRICE_VALUE);
    }
}
