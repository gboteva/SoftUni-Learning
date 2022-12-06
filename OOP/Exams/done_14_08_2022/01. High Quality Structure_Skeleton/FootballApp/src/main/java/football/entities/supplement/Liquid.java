package football.entities.supplement;

public class Liquid extends BaseSupplement{

    private final static int ENERGY_VALUE = 90;
    private final static int ENERGY_PRICE = 25;

    public Liquid() {
        super(ENERGY_VALUE, ENERGY_PRICE);
    }
}
