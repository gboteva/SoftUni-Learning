package DefiningClasses.ex.P04_RawData;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String [] info = scanner.nextLine().split("\\s+");
            String model = info[0];
            int speed = Integer.parseInt(info[1]);
            int power = Integer.parseInt(info[2]);
            int weight = Integer.parseInt(info[3]);
            String type = info[4];
            double pressure1 = Double.parseDouble(info[5]);
            int age1 = Integer.parseInt(info[6]);
            double pressure2 = Double.parseDouble(info[7]);
            int age2 = Integer.parseInt(info[8]);
            double pressure3 = Double.parseDouble(info[9]);
            int age3 = Integer.parseInt(info[10]);
            double pressure4 = Double.parseDouble(info[11]);
            int age4 = Integer.parseInt(info[12]);

           Engine engine = new Engine(speed, power);
           Cargo cargo = new Cargo(weight, type);
           Tire tire1 = new Tire(age1, pressure1);
           Tire tire2 = new Tire(age2, pressure2);
           Tire tire3 = new Tire(age3, pressure3);
           Tire tire4 = new Tire(age4, pressure4);

           Car car = new Car(model, engine, cargo, tire1, tire2, tire3, tire4);
           cars.add(car);
        }

        String command = scanner.nextLine();
        if (command.equals("fragile")){
            cars.stream().forEach(car->{
                if (car.getCargo().getType().equals("fragile") &&
                        (car.getTire1().getPressure()<1 || car.getTire2().getPressure()<1
                || car.getTire3().getPressure()<1 || car.getTire4().getPressure()<1)){
                    System.out.println(car.getModel());
                }
            });

        }else if (command.equals("flamable")){
            cars.stream().forEach(car->{
                if (car.getCargo().getType().equals("flamable")
                        && car.getEngine().getPower()>250){
                    System.out.println(car.getModel());
                }

            });
        }


    }
}
