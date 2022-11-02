package pizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] pizzaInfo = scanner.nextLine().split("\\s+");
        String pizzaName = pizzaInfo[1];
        int numberOfToppings = Integer.parseInt(pizzaInfo[2]);

        String[] doughInfo = scanner.nextLine().split("\\s+");
        String doughType = doughInfo[1];
        String backingTechnique = doughInfo[2];
        double doughWeight = Double.parseDouble(doughInfo[3]);

        String command = scanner.nextLine();

        try {
            Pizza pizza = new Pizza(pizzaName, numberOfToppings);
            Dough dough = new Dough(doughType, backingTechnique, doughWeight);
            pizza.setDough(dough);
            while (!"END".equals(command)) {
                String[] toppingInfo = command.split("\\s+");
                String type = toppingInfo[1];
                double weigh = Double.parseDouble(toppingInfo[2]);
                Topping topping = new Topping(type, weigh);
                pizza.addTopping(topping);

                command = scanner.nextLine();
            }

            System.out.println(pizza);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

