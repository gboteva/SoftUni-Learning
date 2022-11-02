package shoppingSpree;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] peopleInfo = scanner.nextLine().split(";");
        String[] productsInfo = scanner.nextLine().split(";");

        Map<String, Person> people = new LinkedHashMap<>();

        for (String string : peopleInfo) {
            String name = string.split("=")[0];
            double money = Double.parseDouble(string.split("=")[1]);

            try {
                Person person = new Person(name, money);
                people.putIfAbsent(name, person);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        Map<String, Product> products = new LinkedHashMap<>();
        for (String string : productsInfo) {
            String name = string.split("=")[0];
            double cost = Double.parseDouble(string.split("=")[1]);

            try {
                Product product = new Product(name, cost);
                products.putIfAbsent(name, product);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        String command = scanner.nextLine();

        while (!"END".equals(command)) {
            String[] tokens = command.split("\\s+");
            String personName = tokens[0];
            String productName = tokens[1];

            try {
                if (people.containsKey(personName)){
                    people.get(personName).buyProduct(products.get(productName));
                    System.out.printf("%s bought %s%n", personName, productName);
                }

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            command = scanner.nextLine();
        }

            people.values().forEach(System.out::println);



    }
}
