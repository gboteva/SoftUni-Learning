package DefiningClasses.ex.P05_CarSalesman;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Engine> engineMap = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] info = scanner.nextLine().split("\\s+");
            String model = info[0];
            int power = Integer.parseInt(info[1]);
            Engine engine;
            if (info.length == 4) {
                String displacement = info[2];
                String efficiency = info[3];
                engine = new Engine(model, power, displacement, efficiency);
            } else if (info.length == 2) {
                engine = new Engine(model, power);
            } else {
                String thing = info[2];
                engine = new Engine(model, power);
                if (Character.isLetter(thing.charAt(0))) {
                    engine.setEfficiency(thing);
                } else {
                    engine.setDisplacement(thing);
                }
            }
            engineMap.put(model, engine);
        }

        int m = Integer.parseInt(scanner.nextLine());
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String[] info = scanner.nextLine().split("\\s+");
            String carModel = info[0];
            String enginModel = info[1];

            Car car = null;
            Engine engineFromMap = null;

            if (engineMap.containsKey(enginModel)){
                engineFromMap = engineMap.get(enginModel);
            }

            if (info.length == 2) {
                car = new Car(carModel, engineFromMap);
            }else if (info.length==4){
                String weight = info[2];
                String color = info[3];
                car = new Car(carModel, engineFromMap, weight, color);
            }else {
                car = new Car(carModel, engineFromMap);
                String thing = info[2];
                if (Character.isDigit(thing.charAt(0))){
                    car.setWeight(thing);
                }else {
                    car.setColor(thing);
                }
            }
            cars.add(car);
        }
        cars.stream().forEach(car-> System.out.println(car.toString()));
    }
}
