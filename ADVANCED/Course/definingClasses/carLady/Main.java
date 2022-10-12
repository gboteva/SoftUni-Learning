package definingClasses.carLady;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Map<String, Cat> cats = new HashMap<>();

        while (!line.equals("End")) {
            String[] data = line.split("\\s+");
            String type = data[0];
            String name = data[1];
            double parameter = Double.parseDouble(data[2]);

            Cat cat = null;
            switch (type){
                case "Siamese":
                    cat = new Siamese(name, parameter);
                    break;
                case "Cymric":
                    cat = new Cymric(name, parameter);
                    break;
                case "StreetExtraordinaire":
                    cat = new StreetExtraordinaire(name, parameter);
                    break;
            }
            cats.put(name, cat);
            line = scanner.nextLine();
        }
        String searchedName = scanner.nextLine();
        System.out.println(cats.get(searchedName));
    }
}
