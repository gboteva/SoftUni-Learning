package ex_p02_VehicleExtension;

public class Car extends Vehicle {
    private final static double AC_ADDITIONAL_CONSUMPTION = 0.9;

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + AC_ADDITIONAL_CONSUMPTION, tankCapacity);
    }
}
