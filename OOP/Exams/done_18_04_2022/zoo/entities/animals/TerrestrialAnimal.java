package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal{

    private final static double INITIAL_KG = 5.50;
    private final static double INCREASING_KG_STEP = 5.70;

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, INITIAL_KG, price);
    }

    @Override
    public void eat() {
        setPrice(getKg() + INCREASING_KG_STEP);
    }
}
