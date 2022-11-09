package vehicles;

public class Car extends Vehicle{

    private final static double summerFuelConsumptionIncreasingStep = 0.9;

    public Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + summerFuelConsumptionIncreasingStep);
    }


    @Override
    public void refuel(double litters) {
        setFuelQuantity(getFuelQuantity() + litters);
    }


}
