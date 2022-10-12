package definingClasses.speedRacing;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        Map<String, Car> cars = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] carData = scanner.nextLine().split("\\s+");
            String model = carData[0];
            double fuelAmount = Double.parseDouble(carData[1]);
            double fuelCostFor1Km = Double.parseDouble(carData[2]);
            Car car = new Car(model, fuelAmount, fuelCostFor1Km);
            cars.put(model, car);
        }

        String line = scanner.nextLine();

        while (!line.equals("End")){
            String [] carData = line.split("\\s+");
            String carModel = carData[1];
            int distance = Integer.parseInt(carData[2]);

            cars.get(carModel).drive(distance);

            line = scanner.nextLine();
        }

        cars.values().forEach(System.out::println);

    }
}
