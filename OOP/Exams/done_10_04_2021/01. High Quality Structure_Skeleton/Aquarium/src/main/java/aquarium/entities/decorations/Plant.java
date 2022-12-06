package aquarium.entities.decorations;

public class Plant extends BaseDecoration{
    private final static int COMFORT_VALUE = 5;
    private final static double PRICE_VALUE = 10;

    public Plant() {
        super(COMFORT_VALUE, PRICE_VALUE);
    }
}
