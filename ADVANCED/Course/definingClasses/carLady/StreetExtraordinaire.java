package definingClasses.carLady;

public class StreetExtraordinaire extends Cat{
    private double decibels;

    public StreetExtraordinaire(String name, double decibels) {
        super(name);
        this.decibels = decibels;
    }

    public double getDecibels() {
        return decibels;
    }

    public String toString() {
        return String.format("%s %s %.2f", getClass().getSimpleName(), getName(), getDecibels()) ;
    }
}
