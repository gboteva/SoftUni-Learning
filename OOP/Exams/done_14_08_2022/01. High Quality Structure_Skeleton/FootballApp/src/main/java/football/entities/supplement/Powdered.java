package football.entities.supplement;

public class Powdered extends BaseSupplement{

    private final static int ENERGY_VALUE = 120;
    private final static double PRICE_VALUE = 15;

    public Powdered() {
        super(ENERGY_VALUE, PRICE_VALUE);
    }
}
