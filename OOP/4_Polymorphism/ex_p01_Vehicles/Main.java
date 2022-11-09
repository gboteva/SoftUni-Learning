package vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] carInfo = scanner.nextLine().split("\\s+");
        String[] truckInfo = scanner.nextLine().split("\\s+");

        double carFuelQuantity = Double.parseDouble(carInfo[1]);
        double carFuelConsumption = Double.parseDouble(carInfo[2]);

        Vehicle car = new Car(carFuelQuantity, carFuelConsumption);

        double truckFuelQuantity = Double.parseDouble(truckInfo[1]);
        double truckFuelConsumption = Double.parseDouble(truckInfo[2]);

        Vehicle truck = new Truck(truckFuelQuantity, truckFuelConsumption);

        int numberOfCommands = Integer.parseInt(scanner.nextLine());

        while (numberOfCommands-- > 0){
            String[] commandPart = scanner.nextLine().split("\\s+");
            String typeOfCommand = commandPart[0];
            String vehicle = commandPart[1];
            double parameter = Double.parseDouble(commandPart[2]);

            switch (typeOfCommand){

                case "Drive":
                    if (vehicle.equals("Car")){
                        car.drive(parameter);
                    }else if (vehicle.equals("Truck")){
                        truck.drive(parameter);
                    }

                    break;

                case "Refuel":
                    if (vehicle.equals("Car")){
                        car.refuel(parameter);
                    }else if (vehicle.equals("Truck")){
                        truck.refuel(parameter);
                    }
                    break;

            }
        }

        System.out.printf("Car: %.2f%n", car.getFuelQuantity());
        System.out.printf("Truck: %.2f", truck.getFuelQuantity());
    }
}
