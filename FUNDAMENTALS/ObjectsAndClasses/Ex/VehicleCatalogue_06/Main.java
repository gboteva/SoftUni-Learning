package beforeCourse.objectsAndClasses_6.ex.VehicleCatalogue_06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Vehicle> cataloge = new ArrayList<>();

        while (!input.equals("End")){
            String type = input.split(" ")[0];
            String model = input.split(" ")[1];
            String color = input.split(" ")[2];
            int horsePower =Integer.parseInt(input.split(" ")[3]);

            Vehicle currentVehicle = new Vehicle(type, model, color, horsePower);

            cataloge.add(currentVehicle);

            input= scanner.nextLine();
        }

        String wantedModel = scanner.nextLine();

        while (!wantedModel.equals("Close the Catalogue")){
            String model = wantedModel;
            cataloge.stream().filter(vehicle -> vehicle.getModel().equals(model)).forEach(vehicle -> System.out.println(vehicle));

            wantedModel= scanner.nextLine();
        }

        List<Vehicle> listOfCars = cataloge.stream().filter(vehicle -> vehicle.getType().equals("Car")).collect(Collectors.toList());
        List<Vehicle> listOfTrucks = cataloge.stream().filter(vehicle -> vehicle.getType().equals("Truck")).collect(Collectors.toList());

        double carsHPAverage = averageHorsePower(listOfCars);
        double trucksHPAverage = averageHorsePower(listOfTrucks);

        System.out.printf("Cars have average horsepower of: %.2f.%n", carsHPAverage);
        System.out.printf("Trucks have average horsepower of: %.2f.%n", trucksHPAverage);

    }
    static double averageHorsePower (List<Vehicle> vehicles){
        if (vehicles.size()==0){
            return 0;
        }else {
            return vehicles.stream().mapToDouble(Vehicle::getHorsePower).sum() / vehicles.size();
        }
    }
}
