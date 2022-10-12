package definingClasses.carLady;

public class Siamese extends Cat {
    private double earSize;


    public Siamese(String name, double earSize) {
        super(name);
        this.earSize = earSize;
    }

    public double getEarSize() {
        return earSize;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", getClass().getSimpleName(), getName(), getEarSize());
    }
}
