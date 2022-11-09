package vehicles;

public class Truck extends Vehicle {
    private final static double summerFuelConsumptionIncreasingStep = 1.6;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, (fuelConsumption + summerFuelConsumptionIncreasingStep), tankCapacity);
    }


    @Override
    public void refuel(double litters) {
        if (litters*0.95 <= 0){
            System.out.println("Fuel must be a positive number");
            return;
        }

        if ((super.getFuelQuantity() + (litters * 0.95)) > getTankCapacity()) {
            System.out.println("Cannot fit fuel in tank");
        } else {
            setFuelQuantity(getFuelQuantity() + (litters * 0.95));
        }

    }
}
