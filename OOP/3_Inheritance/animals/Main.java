package animals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        List<Animal> animals = new ArrayList<>();

        while (!line.equals("Beast!")){
            String animalType = line;
            String[] animalInfo = scanner.nextLine().split("\\s+");
            String name = animalInfo[0];
            int age = Integer.parseInt(animalInfo[1]);
            String gender = animalInfo[2];

            switch (animalType){
                case "Dog":
                    Dog dog = new Dog(name, age, gender);
                    animals.add(dog);
                    break;
                case "Cat":
                    Cat cat = new Cat(name, age, gender);
                    animals.add(cat);
                    break;
                case "Frog":
                    Frog frog = new Frog(name, age, gender);
                    break;
                case "Kitten":
                    Kitten kitten = new Kitten(name, age);
                    break;
                case "Tomcat":
                    Tomcat tomcat = new Tomcat(name, age);
                    break;
            }

            line = scanner.nextLine();
        }

        animals.forEach(System.out::println);
    }
}
