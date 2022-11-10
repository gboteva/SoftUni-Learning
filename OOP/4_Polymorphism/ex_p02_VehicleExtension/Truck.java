package vehicles;

public class Truck extends Vehicle {
    private final static double summerFuelConsumptionIncreasingStep = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, (fuelConsumption + summerFuelConsumptionIncreasingStep), tankCapacity);
    }


    @Override
    public void refuel(double litters) {
       super.refuel(litters * 0.95);

    }
}
