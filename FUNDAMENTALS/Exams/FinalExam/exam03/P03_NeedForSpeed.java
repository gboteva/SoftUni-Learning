package FundamentalsCourse.finalExam.exam03;

import com.sun.source.tree.WhileLoopTree;

import java.util.*;

public class P03_NeedForSpeed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Integer> carDistance = new LinkedHashMap<>();
        Map<String, Integer> carFuel = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String data = scanner.nextLine();
            String car = data.split("\\|")[0];
            int mileage = Integer.parseInt(data.split("\\|")[1]);
            int fuel = Integer.parseInt(data.split("\\|")[2]);
            carDistance.putIfAbsent(car, mileage);
            carFuel.putIfAbsent(car, fuel);
        }

        String command = scanner.nextLine();

        while (!command.equals("Stop")) {
            String action = command.split(" : ")[0];
            switch (action) {
                case "Drive":
                    String car = command.split(" : ")[1];
                    int distance =Integer.parseInt(command.split(" : ")[2]);
                    int fuel = Integer.parseInt(command.split(" : ")[3]);
                    int availableFuel = carFuel.get(car);
                    if (availableFuel>=fuel){
                        carDistance.put(car, carDistance.get(car)+distance);
                        carFuel.put(car, carFuel.get(car)-fuel);
                        System.out.printf("%s driven for %d kilometers. %d liters of fuel consumed.%n", car,distance, fuel );
                        if (carDistance.get(car)>100000){
                            carDistance.remove(car);
                            carFuel.remove(car);
                            System.out.println("Time to sell the " + car + "!");
                        }
                    }else {
                        System.out.println("Not enough fuel to make that ride");
                    }
                    break;
                case "Refuel":
                    String carForRefuel = command.split(" : ")[1];
                    int fuelForRefuel = Integer.parseInt(command.split(" : ")[2]);
                    int availableInCar = carFuel.get(carForRefuel);
                    if (availableInCar+fuelForRefuel>75){
                        carFuel.put(carForRefuel, 75);
                        System.out.printf("%s refueled with %d liters%n", carForRefuel, 75 - availableInCar);
                    }else {
                        carFuel.put(carForRefuel, availableInCar+fuelForRefuel);
                        System.out.printf("%s refueled with %d liters%n", carForRefuel, fuelForRefuel);
                    }

                    break;
                case "Revert":
                    String currentCar = command.split(" : ")[1];
                    int kilometers = Integer.parseInt(command.split(" : ")[2]);
                    int availableKm = carDistance.get(currentCar);
                    if (availableKm-kilometers<10000){
                        carDistance.put(currentCar, 10000);
                    }else {
                        carDistance.put(currentCar, availableKm-kilometers);
                        System.out.printf("%s mileage decreased by %d kilometers%n", currentCar, kilometers);
                    }
                    break;
            }
            command = scanner.nextLine();
        }

        Map<String, List<Integer>> finalMap = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : carDistance.entrySet() ){
            finalMap.put(entry.getKey(), new ArrayList<>());
            finalMap.get(entry.getKey()).add(entry.getValue());
        }

        for (Map.Entry<String, Integer> entry : carFuel.entrySet() ){
            finalMap.get(entry.getKey()).add(entry.getValue());
        }

        //key -> car; value[0]->distance; value[1] -> fuel

        for ( Map.Entry<String, List<Integer>> entry: finalMap.entrySet()){
            System.out.printf("%s -> Mileage: %d kms, Fuel in the tank: %d lt.%n", entry.getKey(), entry.getValue().get(0), entry.getValue().get(1));
        }

    }
}
