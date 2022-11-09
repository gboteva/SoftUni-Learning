package vehicles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();

        for (int i = 0; i < 3; i++) {

            String[] vehicleInfo = scanner.nextLine().split("\\s+");

            String vehicleType = vehicleInfo[0];
            double fuelQuantity = Double.parseDouble(vehicleInfo[1]);
            double fuelConsumption = Double.parseDouble(vehicleInfo[2]);
            double tankCapacity = Double.parseDouble(vehicleInfo[3]);

            Vehicle vehicle;
            if (vehicleType.equals("Car")) {
                vehicle = new Car(fuelQuantity, fuelConsumption, tankCapacity);
                vehicleMap.put("Car", vehicle);
            } else if (vehicleType.equals("Truck")) {
                vehicle = new Truck(fuelQuantity, fuelConsumption, tankCapacity);
                vehicleMap.put("Truck", vehicle);
            } else {
                vehicle = new Bus(fuelQuantity, fuelConsumption, tankCapacity);
                vehicleMap.put("Bus", vehicle);
            }
        }


        int numberOfCommands = Integer.parseInt(scanner.nextLine());

        while (numberOfCommands-- > 0) {
            String[] commandPart = scanner.nextLine().split("\\s+");
            String typeOfCommand = commandPart[0];
            String vehicleType = commandPart[1];
            double parameter = Double.parseDouble(commandPart[2]);

            switch (typeOfCommand) {

                case "Drive":
                        vehicleMap.get(vehicleType).drive(parameter);
                    break;

                case "Refuel":
                        vehicleMap.get(vehicleType).refuel(parameter);
                    break;

                case "DriveEmpty":
                   Bus bus = (Bus) vehicleMap.get(vehicleType);
                   bus.setIsEmpty();
                   bus.drive(parameter);
                   break;

            }
        }

        vehicleMap.values()
                .forEach(v-> System.out.printf("%s: %.2f%n", v.getClass().getSimpleName(), v.getFuelQuantity()));

    }
}
