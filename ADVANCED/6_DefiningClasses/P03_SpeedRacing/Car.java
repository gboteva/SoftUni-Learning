package DefiningClasses.ex.P03_SpeedRacing;

import java.util.Map;

public class Car {
    private String model;
    private double fuelAmount;
    private double littresPerKm;
    private double distance;

    public Car (String model, double fuelAmount, double fuelCostPerKm){
        this.model = model;
        this.fuelAmount = fuelAmount;
        this.littresPerKm = fuelCostPerKm;
        this.distance = 0;
    }

    public double getFuelAmount(){
        return this.fuelAmount;
    }

    public void setDistance(double km){
        this.distance = this.distance+km;
    }

    public void setFuelAmount(double neededFuel){
        this.fuelAmount = this.fuelAmount-neededFuel;
    }

    public String getModel() {
        return model;
    }

    public double getLittresPerKm() {
        return littresPerKm;
    }

    public double getDistance() {
        return distance;
    }

    public void moveCar (double km, Car currentCar, Map<String, Car> carsMap){
        if (carsMap.containsKey(currentCar.getModel())){
            double neededLitres = km * currentCar.getLittresPerKm();
            if (neededLitres<=currentCar.getFuelAmount()){
                currentCar.setDistance(km);
                currentCar.setFuelAmount(neededLitres);
            } else {
                System.out.println("Insufficient fuel for the drive");
            }
        }


    }

    public String toString (){
        return String.format("%s %.2f %.0f", this.model, this.fuelAmount, this.distance);
    }
}
