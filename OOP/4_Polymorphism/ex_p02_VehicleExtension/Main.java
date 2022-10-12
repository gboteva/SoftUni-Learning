package ex_p02_VehicleExtension;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();

        fillVehicleMap(scanner, vehicleMap);

        int numberOfCommands = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberOfCommands; i++) {
            String[] line = scanner.nextLine().split("\\s+");
            String command = line[0];
            String vehicleType = line[1];

            switch (command) {
                case "Drive":
                    double distance = Double.parseDouble(line[2]);
                    System.out.println(vehicleMap.get(vehicleType).drive(distance));
                    break;

                case "Refuel":
                    double litter = Double.parseDouble(line[2]);
                    try {
                        vehicleMap.get(vehicleType).refuel(litter);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case "DriveEmpty":
                    if (line[1].equals("Bus")) {
                        double ADDITIONAL_FUEL_CONSUMPTION = 1.4;
                        double distanceToEmptyDrive = Double.parseDouble(line[2]);
                        double currentFuelConsumption = vehicleMap.get(vehicleType).getFuelConsumption();
                        double consumptionOfEmptyBus = vehicleMap.get(vehicleType).getFuelConsumption() - ADDITIONAL_FUEL_CONSUMPTION;

                        vehicleMap.get(vehicleType).setFuelConsumption(consumptionOfEmptyBus);
                        System.out.println(vehicleMap.get(vehicleType).drive(distanceToEmptyDrive));
                        vehicleMap.get(vehicleType).setFuelConsumption(currentFuelConsumption);

                    }

                    break;
            }
        }

        vehicleMap.entrySet().stream().forEach(v -> {
            System.out.printf("%s: %.2f%n",
                    v.getValue().getClass().getSimpleName(), v.getValue().getFuelQuantity());
        });
    }

    private static void fillVehicleMap(Scanner scanner, Map<String, Vehicle> vehicleMap) {
        for (int i = 0; i < 3; i++) {
            String[] vehicleInfo = scanner.nextLine().split("\\s+");
            Vehicle vehicle = creatNewVehicle(vehicleInfo);
            if (vehicle instanceof Car) {
                vehicleMap.put("Car", vehicle);
            } else if (vehicle instanceof Truck) {
                vehicleMap.put("Truck", vehicle);
            } else {
                vehicleMap.put("Bus", vehicle);
            }
        }
    }

    private static Vehicle creatNewVehicle(String[] vehicleInfo) {
        String type = vehicleInfo[0];
        double initialFuelQuantity = Double.parseDouble(vehicleInfo[1]);
        double fuelConsumption = Double.parseDouble(vehicleInfo[2]);
        double tankCapacity = Double.parseDouble(vehicleInfo[3]);
        switch (type) {
            case "Car":
                return new Car(initialFuelQuantity, fuelConsumption, tankCapacity);

            case "Truck":
                return new Truck(initialFuelQuantity, fuelConsumption, tankCapacity);

            case "Bus":
                return new Bus(initialFuelQuantity, fuelConsumption, tankCapacity);

        }
        return null;
    }
}
