package vehicles;

public class Truck extends Vehicle{
    private final static double summerFuelConsumptionIncreasingStep = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + summerFuelConsumptionIncreasingStep);
    }


    @Override
    public void refuel(double litters) {
        setFuelQuantity(getFuelQuantity() + (litters*0.95));
    }
}
