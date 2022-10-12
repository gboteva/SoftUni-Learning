package FundamentalsCourse.objectsAndClasses_6.MoreEx.RawData_02;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] data = scanner.nextLine().split("\\s+");
            String model = data[0];
            int engineSpeed =Integer.parseInt(data[1]);
            int enginePower = Integer.parseInt(data[2]);
            Engine engine = new Engine(engineSpeed, enginePower);

            int cargoWeight = Integer.parseInt(data[3]);
            String cargoType = data[4];
            Cargo cargo = new Cargo(cargoWeight, cargoType);

            List<Tire> tires = new ArrayList<>();

            double press1 = Double.parseDouble(data[5]);
            int age1 = Integer.parseInt(data[6]);
            Tire tire1 = new Tire(press1, age1);

            double press2 = Double.parseDouble(data[7]);
            int age2 = Integer.parseInt(data[8]);
            Tire tire2 = new Tire(press2, age2);

            double press3 = Double.parseDouble(data[9]);
            int age3 = Integer.parseInt(data[10]);
            Tire tire3 = new Tire(press3, age3);

            double press4 = Double.parseDouble(data[11]);
            int age4 = Integer.parseInt(data[12]);
            Tire tire4 = new Tire(press4, age4);
            tires.add(tire1);
            tires.add(tire2);
            tires.add(tire3);
            tires.add(tire4);

            Car car = new Car(model, engine, cargo, tires);
            cars.add(car);

        }

        String type = scanner.nextLine();
        Set<Car> smallPressure = new LinkedHashSet<>();
       if (type.equals("fragile")){
           for (Car car : cars ){
               if (car.getCargo().getType().equals("fragile")){
                   car.getTires().forEach(tire -> {
                      if(tire.getPressure()<1){
                         smallPressure.add(car);
                      }
                   });
               }
           }
           for (Car car : smallPressure ){
               System.out.println(car.getModel());
           }
       } else if (type.equals("flamable")){
           for ( Car car: cars){
               if (car.getCargo().getType().equals("flamable")){
                   if (car.getEngine().getPower()>250){
                       System.out.println(car.getModel());
                   }
               }
           }
       }

    }
}
