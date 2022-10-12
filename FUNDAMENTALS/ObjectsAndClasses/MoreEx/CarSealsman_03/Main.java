package FundamentalsCourse.objectsAndClasses_6.MoreEx.CarSealsman_03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<Engine> engines = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] engineData = scanner.nextLine().split("\\s+");
            String model = engineData[0];
            int power = Integer.parseInt(engineData[1]);
            Engine engine = new Engine(model, power);

            if (engineData.length == 3) {
                String nextInfo = engineData[2];
                if (nextInfo.length() == 1) {
                    char symbol = nextInfo.charAt(0);
                    if (Character.isLetter(symbol)) {
                        String efficiency = "" + symbol;
                        engine.setEfficiency(efficiency);
                    } else if (Character.isDigit(symbol)) {
                        String displacement = "" + symbol;
                        engine.setDisplacement(displacement);
                    }
                } else {
                    if (nextInfo.contains("+")) {
                        String efficiency = nextInfo;
                        engine.setEfficiency(efficiency);
                    } else {
                        String displacement = nextInfo;
                        engine.setDisplacement(displacement);
                    }
                }

            } else if (engineData.length == 4) {
                String displacement = engineData[2];
                String efficiency = engineData[3];
                engine.setDisplacement(displacement);
                engine.setEfficiency(efficiency);
            }

            engines.add(engine);
        }

        int m = Integer.parseInt(scanner.nextLine());
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String[] carData = scanner.nextLine().split("\\s+");
            String model = carData[0];
            String givenEnginModel = carData[1];
            Engine engine = null;
            for (Engine currentEngine : engines) {
                if (currentEngine.getModel().equals(givenEnginModel)) {
                    engine = currentEngine;
                }
            }
            Car car = new Car(model, engine);
            if (carData.length==3){
                String nextInfo = carData[2];
                if (Character.isDigit(nextInfo.charAt(0))){
                    String weight = nextInfo;
                    car.setWeight(weight);
                }else {
                    String color = nextInfo;
                    car.setColor(color);
                }
            }else if (carData.length==4){
                String weight = carData[2];
                String color = carData[3];
                car.setWeight(weight);
                car.setColor(color);
            }
            cars.add(car);
        }

        for (Car car : cars ){
            System.out.println(car.toString());
        }

    }
}
