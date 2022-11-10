package vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {

    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;

    protected Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    protected double getFuelQuantity() {
        return fuelQuantity;
    }

    protected double getFuelConsumption() {
        return fuelConsumption;
    }

    protected double getTankCapacity() {
        return tankCapacity;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    protected void setFuelConsumption(double fuelConsumption){
        this.fuelConsumption = fuelConsumption;
    }

    public void drive(double km) {

        double neededFuel = km * getFuelConsumption();

        if (haveEnoughFuel(neededFuel)) {

            setFuelQuantity(getFuelQuantity() - neededFuel);

            printDrivingOutput(km);

        } else {
            System.out.printf("%s needs refueling%n", getClass().getSimpleName());
        }

    }

    public void refuel(double litters){
        if (litters <= 0){
            System.out.println("Fuel must be a positive number");
            return;
        }
        if (getFuelQuantity() + litters > getTankCapacity()){
            System.out.println("Cannot fit fuel in tank");
        }else {
            setFuelQuantity(getFuelQuantity() + litters);
        }
    }

    private boolean haveEnoughFuel(double neededFuel) {
        if (neededFuel > fuelQuantity) {
            return false;
        }
        return true;
    }

    private void printDrivingOutput(double km) {
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.printf("%s travelled %s km%n", getClass().getSimpleName(), df.format(km));
    }
}
