package catHouse.entities.houses;

public class ShortHouse extends BaseHouse{

    private final static int CAPACITY_VALUE = 15;

    public ShortHouse(String name) {
        super(name, CAPACITY_VALUE);
    }
}
