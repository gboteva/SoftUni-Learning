package DefiningClasses.ex.P03_SpeedRacing;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Car> carsMap = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String [] token = scanner.nextLine().split("\\s+");
            String model = token[0];
            double fuelAmount = Double.parseDouble(token[1]);
            double fuelCost = Double.parseDouble(token[2]);
            Car car = new Car(model, fuelAmount, fuelCost);
            carsMap.put(model, car);
        }

        String command = scanner.nextLine();

        while (!command.equals("End")){
            String [] token = command.split("\\s+");
            String model = token[1];
            double km = Double.parseDouble(token[2]);

            if (carsMap.containsKey(model)){
                Car currentCar = carsMap.get(model);
                currentCar.moveCar(km, currentCar, carsMap);
            }

            command = scanner.nextLine();
        }

        carsMap.entrySet().stream()
                .forEach(entry-> System.out.println(entry.getValue().toString()));

    }
}
