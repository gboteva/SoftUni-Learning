package definingClasses.speedRacing;

public class Car {
    private String model;
    private  double fuelAmount;
    private double fuelCostFor1Km;
    private int distanceTraveled;

    public Car(String model, double fuelAmount, double fuelCostFor1Km) {
        this.model = model;
        this.fuelAmount = fuelAmount;
        this.fuelCostFor1Km = fuelCostFor1Km;
        this.distanceTraveled = 0;
    }

    protected double calculateNeededFuel (int distance){
        return  distance * fuelCostFor1Km;
    }

    protected boolean hasEnoughFuel (int distance){
        return this.fuelAmount >= calculateNeededFuel(distance);
    }

    protected void drive (int distance){
        if (hasEnoughFuel(distance)){
            this.fuelAmount -= calculateNeededFuel(distance);
            distanceTraveled += distance;
        } else {
            System.out.println("Insufficient fuel for the drive");
        }
    }

    @Override
    public String toString() {
        return String.format("%s %.2f %d", this.model, this.fuelAmount, this.distanceTraveled);
    }
}
