package vehicles;

public class Car extends Vehicle{

    private final static double summerFuelConsumptionIncreasingStep = 0.9;

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, (fuelConsumption + summerFuelConsumptionIncreasingStep), tankCapacity);
    }


}
