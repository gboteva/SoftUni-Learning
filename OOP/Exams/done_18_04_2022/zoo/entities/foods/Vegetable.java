package zoo.entities.foods;

public class Vegetable extends BaseFood{

    private final static int DEFAULT_CALORIES = 50;
    private final static double DEFAULT_PRICE = 5;

    public Vegetable() {
        super(DEFAULT_CALORIES, DEFAULT_PRICE);
    }
}
