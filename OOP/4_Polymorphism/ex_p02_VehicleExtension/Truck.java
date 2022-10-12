package ex_p02_VehicleExtension;

public class Truck extends Vehicle {
    private final static double AC_ADDITIONAL_CONSUMPTION = 1.6;
    private final static double MAX_CAPACITY_LEVEL_OF_TANK = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + AC_ADDITIONAL_CONSUMPTION, tankCapacity);
    }

    @Override
    void refuel(double litters){
        super.refuel(litters* MAX_CAPACITY_LEVEL_OF_TANK);
    }
}
