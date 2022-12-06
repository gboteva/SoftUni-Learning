package zoo.entities.foods;

public class Meat extends BaseFood{

    private final static int DEFAULT_CALORIES = 70;
    private final static double DEFAULT_PRICE = 10;

    public Meat() {
        super(DEFAULT_CALORIES, DEFAULT_PRICE);
    }
}
