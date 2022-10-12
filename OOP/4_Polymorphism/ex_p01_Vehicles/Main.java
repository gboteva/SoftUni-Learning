package ex_p01_Vehicles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] carInfo = scanner.nextLine().split("\\s+");
        String[] truckInfo = scanner.nextLine().split("\\s+");
        Car car = new Car(Double.parseDouble(carInfo[1]), Double.parseDouble(carInfo[2]));
        Truck truck = new Truck(Double.parseDouble(truckInfo[1]), Double.parseDouble(truckInfo[2]));

        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();
        vehicleMap.put("Car", car);
        vehicleMap.put("Truck", truck);

        int commandCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < commandCount; i++) {
            String[] line = scanner.nextLine().split("\\s+");
            String command = line[0];
            String vehicleType = line[1];

            switch (command){
                case "Drive":
                    double distance = Double.parseDouble(line[2]);
                    System.out.println(vehicleMap.get(vehicleType).drive(distance));
                    break;

                case "Refuel":
                    double litter = Double.parseDouble(line[2]);
                    vehicleMap.get(vehicleType).refuel(litter);
                    break;
            }
        }

       vehicleMap.entrySet().stream().forEach(v->{
           System.out.printf("%s: %.2f%n",
                   v.getValue().getClass().getSimpleName(), v.getValue().getFuelQuantity());
       });
    }
}
