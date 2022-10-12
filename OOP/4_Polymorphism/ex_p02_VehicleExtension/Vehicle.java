package ex_p02_VehicleExtension;

import java.text.DecimalFormat;

public class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;

    public Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.setFuelQuantity(fuelQuantity);
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        if (fuelQuantity <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }

        this.fuelQuantity = fuelQuantity;

    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    String drive(double distance) {
        double neededFuel = distance * this.fuelConsumption;

        if (this.fuelQuantity < neededFuel) {
            return String.format("%s needs refueling", getClass().getSimpleName());
        } else {
            this.fuelQuantity -= neededFuel;
            DecimalFormat df = new DecimalFormat("###.##");
            return String.format("%s travelled %s km", getClass().getSimpleName(), df.format(distance));
        }
    }

    void refuel(double litters) {
        double quantityToRefuel = this.fuelQuantity + litters;
        if (quantityToRefuel > this.tankCapacity) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        } else  if (litters <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        this.fuelQuantity += litters;
    }
}
