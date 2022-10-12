package ex_p02_VehicleExtension;

public class Bus extends Vehicle{

    private static final double ADDITIONAL_AC_CONSUMPTION = 1.4;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + ADDITIONAL_AC_CONSUMPTION, tankCapacity);
    }




}
