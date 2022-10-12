package ex_p01_Vehicles;

import java.text.DecimalFormat;

public class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;

    public Vehicle(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    String drive (double distance){
        double neededFuel = distance * this.fuelConsumption;
        if (this.fuelQuantity<neededFuel){
            return String.format("%s needs refueling", getClass().getSimpleName());
        }else {
            this.fuelQuantity-=neededFuel;
            DecimalFormat df = new DecimalFormat("###.##");
            return String.format("%s travelled %s km", getClass().getSimpleName(), df.format(distance));
        }
    }

    void refuel(double litters){
        this.fuelQuantity+=litters;
    }
}
