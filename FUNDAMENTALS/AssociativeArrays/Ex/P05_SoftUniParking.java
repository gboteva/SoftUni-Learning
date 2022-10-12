package beforeCourse.AssociativeArrays_7.ex;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class P05_SoftUniParking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numbersComands = Integer.parseInt(scanner.nextLine());
        Map<String, String> parking = new LinkedHashMap<>();

        for (int i = 0; i <numbersComands ; i++) {
            String [] line = scanner.nextLine().split(" ");
            String command = line[0];
            String user = line[1];

           if (command.equals("register")){
               String number = line[2];
                if (parking.containsKey(user)){
                    System.out.printf("ERROR: already registered with plate number %s%n", number);
                }else {
                    parking.put(user,number);
                    System.out.printf("%s registered %s successfully%n", user,number);
                }
           }else {
                if (!parking.containsKey(user)){
                    System.out.printf("ERROR: user %s not found%n", user);
                }else {
                    parking.remove(user);
                    System.out.printf("%s unregistered successfully%n", user);
                }
           }

        }
        parking.entrySet().forEach(entry -> System.out.printf("%s => %s%n", entry.getKey(), entry.getValue()));
    }
}
