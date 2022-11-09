package vehicles;

public class Car extends Vehicle{

    private final static double summerFuelConsumptionIncreasingStep = 0.9;

    public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, (fuelConsumption + summerFuelConsumptionIncreasingStep), tankCapacity);
    }


    @Override
    public void refuel(double litters) {
        if (litters <= 0){
            System.out.println("Fuel must be a positive number");
            return;
        }

        if (super.getFuelQuantity() + litters > getTankCapacity()){
            System.out.println("Cannot fit fuel in tank");
        }else {
            setFuelQuantity(getFuelQuantity() + litters);
        }


    }


}
