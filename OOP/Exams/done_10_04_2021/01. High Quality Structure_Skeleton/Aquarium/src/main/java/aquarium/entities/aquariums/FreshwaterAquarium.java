package aquarium.entities.aquariums;

public class FreshwaterAquarium extends BaseAquarium{
    private final static int CAPACITY_VALUE = 50;

    public FreshwaterAquarium(String name) {
        super(name, CAPACITY_VALUE);
    }
}
