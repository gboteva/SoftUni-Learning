package setsAndMaps;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class ParkingLot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Set<String> parking = new LinkedHashSet<>();

        while (!input.equals("END")){
            String[] tokens = input.split(", ");
            switch (tokens[0]){
                case "IN":
                    parking.add(tokens[1]);
                    break;
                case "OUT":
                    parking.remove(tokens[1]);
                    break;
            }


            input = scanner.nextLine();
        }

        if (parking.isEmpty()){
            System.out.println("Parking Lot is Empty");
        }else {
            parking.stream().forEach(System.out::println);
        }

    }
}
