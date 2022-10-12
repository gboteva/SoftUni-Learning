package ex_p04_pizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
        String[] pizzaInfo = scanner.nextLine().split("\\s+");
        String[] doughInfo = scanner.nextLine().split("\\s+");

        String pizzaName = pizzaInfo[1];
        int numberOfToppings = Integer.parseInt(pizzaInfo[2]);
        Pizza pizza = new Pizza(pizzaName, numberOfToppings);

        String doughType = doughInfo[1];
        String doughBakingTechnique = doughInfo[2];
        double doughWeigh = Double.parseDouble(doughInfo[3]);
        Dough dough = new Dough(doughType, doughBakingTechnique, doughWeigh);
        pizza.setDough(dough);

        String toppingInfo = scanner.nextLine();

        while (!toppingInfo.equals("END")){
            String toppingType = toppingInfo.split("\\s+")[1];
            double weight = Double.parseDouble(toppingInfo.split("\\s+")[2]);

            Topping topping = new Topping(toppingType, weight);
            pizza.addTopping(topping);

            toppingInfo = scanner.nextLine();
        }

        System.out.printf("%s - %.2f", pizza.getName(),pizza.getOverallCalories());
    }
}
