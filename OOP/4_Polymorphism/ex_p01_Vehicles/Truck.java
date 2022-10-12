package ex_p01_Vehicles;

public class Truck extends Vehicle {
    private final static double AC_ADDITIONAL_CONSUMPTION = 1.6;
    private final static double MAX_CAPACITY_LEVEL_OF_TANK = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + AC_ADDITIONAL_CONSUMPTION);
    }

    @Override
    void refuel(double litters){
        super.refuel(litters* MAX_CAPACITY_LEVEL_OF_TANK);
    }
}
