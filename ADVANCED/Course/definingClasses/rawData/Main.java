package definingClasses.rawData;

import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Tire> tires = new ArrayList<>();
            String [] tokens = scanner.nextLine().split("\\s+");
            String model = tokens[0];
            int engineSpeed = Integer.parseInt(tokens[1]);
            int enginePower = Integer.parseInt(tokens[2]);
            Engine engine = new Engine(engineSpeed, enginePower);

            int cargoWeight = Integer.parseInt(tokens[3]);
            String cargoType = tokens[4];
            Cargo cargo = new Cargo(cargoWeight, cargoType);

            double pressureTire1 = Double.parseDouble(tokens[5]);
            int ageTire1 = Integer.parseInt(tokens[6]);
            Tire tire1 = new Tire(pressureTire1, ageTire1);
            tires.add(tire1);

            double pressureTire2 = Double.parseDouble(tokens[7]);
            int ageTire2 = Integer.parseInt(tokens[8]);
            Tire tire2 = new Tire(pressureTire2, ageTire2);
            tires.add(tire2);

            double pressureTire3 = Double.parseDouble(tokens[9]);
            int ageTire3 = Integer.parseInt(tokens[10]);
            Tire tire3 = new Tire(pressureTire3, ageTire3);
            tires.add(tire3);

            double pressureTire4 = Double.parseDouble(tokens[11]);
            int ageTire4 = Integer.parseInt(tokens[12]);
            Tire tire4 = new Tire(pressureTire4, ageTire4);
            tires.add(tire4);

            Car car = new Car(model, engine, cargo, tires);
            cars.add(car);
        }


        String searchedType = scanner.nextLine();
        if (searchedType.equals("fragile")){
            for (Car car : cars) {
                if (car.getCargo().getType().equals(searchedType)) {
                    Collection<Tire> tires = car.getTires();
                    for (Tire tire : tires) {
                        if (tire.getPressure()<1) {
                            System.out.println(car.getModel());
                            break;
                        }
                    }

                }
            }

        }else if (searchedType.equals("flamable")){
            cars.stream().filter(car->car.getCargo().getType().equals(searchedType))
                    .filter(car -> car.getEngine().getPower()>250)
                    .forEach(car -> System.out.println(car.getModel()));
        }

    }
}
