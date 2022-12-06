package football.entities.field;

public class NaturalGrass extends BaseField{
    private final static int CAPACITY_VALUE = 250;

    public NaturalGrass(String name) {
        super(name, CAPACITY_VALUE);
    }
}
