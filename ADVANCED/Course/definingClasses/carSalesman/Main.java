package definingClasses.carSalesman;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Engine> engines = new HashMap<>();
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            Engine engine = new Engine(tokens[0], Integer.parseInt(tokens[1]));
            if (tokens.length == 3){
                if (Character.isLetter(tokens[2].charAt(0))){
                    engine.setEfficiency(tokens[2]);
                }else {
                    engine.setDisplacement(tokens[2]);
                }
            }else if (tokens.length == 4){
                engine.setDisplacement(tokens[2]);
                engine.setEfficiency(tokens[3]);
            }
            engines.putIfAbsent(engine.getModel(), engine);
        }

        int m = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < m; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            Car car = new Car(tokens[0], engines.get(tokens[1]));

            if (tokens.length == 3){
                if (Character.isDigit(tokens[2].charAt(0))){
                    car.setWeight(tokens[2]);
                }else {
                    car.setColor(tokens[2]);
                }
            }else if (tokens.length == 4) {
                car.setWeight(tokens[2]);
                car.setColor(tokens[3]);
            }
            cars.add(car);
        }
        cars.forEach(System.out::println);
    }
}
