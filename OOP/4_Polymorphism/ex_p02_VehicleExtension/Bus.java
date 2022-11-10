package vehicles;

public class Bus extends Vehicle {

    private final static double fuelConsumptionIncreasingStep = 1.4;
    private static double initialFuelConsumption = 0;

    private boolean isEmpty;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
        initialFuelConsumption = fuelConsumption;
        isEmpty = false;
    }

    public void setIsEmpty() {
        isEmpty = true;
        setFuelConsumption(initialFuelConsumption);
    }

    @Override
    public void drive(double km) {
        if (!isEmpty) {
            setFuelConsumption(getFuelConsumption() + fuelConsumptionIncreasingStep);
        }

        super.drive(km);

    }
}
