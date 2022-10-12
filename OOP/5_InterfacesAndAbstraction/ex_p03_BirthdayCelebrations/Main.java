package ex_p03_BirthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        List<Birthable> birthables = new ArrayList<>();

        while (!line.equals("End")){
            String[] tokens = line.split("\\s+");

            switch (tokens[0]){
                case "Citizen":
                    String name = tokens[1];
                    int age = Integer.parseInt(tokens[2]);
                    String id = tokens[3];
                    String birthDate = tokens[4];
                    Citizen citizen = new Citizen(name, age, id, birthDate);
                    birthables.add(citizen);
                    break;
                case "Robot":
                    String model = tokens[1];
                    String robotId = tokens[2];
                    Robot robot = new Robot(robotId, model);
                    break;
                case "Pet":
                    String petName = tokens[1];
                    String petBirtDate = tokens[2];
                    Pet pet = new Pet(petName, petBirtDate);
                    birthables.add(pet);
                    break;

            }

            line = scanner.nextLine();
        }

        String searchedYear = scanner.nextLine();

            birthables.stream().forEach(b->{
                if (b.getBirthDate().endsWith(searchedYear)){
                    System.out.println(b.getBirthDate());
                }
            });

    }
}
