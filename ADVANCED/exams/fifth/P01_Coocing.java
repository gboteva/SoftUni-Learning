package exams.fifth;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class P01_Coocing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split("\\s+");
        String[] secondLine = scanner.nextLine().split("\\s+");

        ArrayDeque<String> liquids = new ArrayDeque<>(); // queue
        //start from the first liquid
        for (String n : firstLine) {
            liquids.offer(n);       //пълня го като опашка, за да мога да взимам първия
        }

        ArrayDeque<String> ingredients = new ArrayDeque<>(); //stack
        //try to mix it with the last ingredient
        for (String n : secondLine) {
            ingredients.push(n);  //пълня го като стек, за да мога да взимам последния
        }

        Map<String, Integer> products = new TreeMap<>();
        products.put("Bread", 0);
        products.put("Cake", 0);
        products.put("Fruit Pie", 0);
        products.put("Pastry", 0);
        // liquids: 10, 20, 30, 40, 50;
        // ingredients: 15, 30, 30, 40, 50

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {
            int liquid = Integer.parseInt(liquids.pop()); //взимам най-горния ->
            int ingredient = Integer.parseInt(ingredients.poll()); //взимам последния
            int result = liquid + ingredient;

            if (result == 25) {
                int currentQuantity = products.get("Bread");
                products.put("Bread", currentQuantity + 1);


            } else if (result == 50) {
                int currentQuantity = products.get("Cake");
                products.put("Cake", currentQuantity + 1);


            } else if (result == 75) {
                int currentQuantity = products.get("Pastry");
                products.put("Pastry", currentQuantity + 1);


            } else if (result == 100) {
                int currentQuantity = products.get("Fruit Pie");
                products.put("Fruit Pie", currentQuantity + 1);


            } else {
                ingredient += 3;
                ingredients.push(String.valueOf(ingredient));
            }

        }

        if (products.get("Bread") > 0 && products.get("Cake") > 0 && products.get("Pastry") > 0
                && products.get("Fruit Pie") > 0) {
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        }else {
            System.out.println("Ugh, what a pity! You didn't have enough materials to cook everything.");
        }

        if (liquids.isEmpty()){
            System.out.println("Liquids left: none");
        }else {
            System.out.print("Liquids left: ");
            System.out.println(String.join(", ", liquids));
        }

        if (ingredients.isEmpty()){
            System.out.println("Ingredients left: none");
        }else {
            System.out.print("Ingredients left: ");
            System.out.println(String.join(", ", ingredients));
        }

        products.entrySet()
                .forEach(entry-> System.out.println(entry.getKey() + ": " + entry.getValue()));

    }
}
