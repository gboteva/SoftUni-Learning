package exams.august2021;

import java.lang.reflect.Array;
import java.util.*;

public class PastryShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] fl = scanner.nextLine().split("\\s+");
        String[] sl = scanner.nextLine().split("\\s+");
        ArrayDeque<Integer> liquids = new ArrayDeque<>();
        Arrays.stream(fl).map(Integer::parseInt).forEach(liquids::offer);

        ArrayDeque<Integer> ingredients = new ArrayDeque<>();
        Arrays.stream(sl).map(Integer::parseInt).forEach(ingredients::push);

        Map<String, Integer> foods = new LinkedHashMap<>();
        foods.put("Biscuit", 0);
        foods.put("Cake", 0);
        foods.put("Pie", 0);
        foods.put("Pastry", 0);

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {
            int liquid = liquids.poll();
            int ingredient = ingredients.pop();
            int sum = liquid + ingredient;

            switch (sum) {
                case 25:
                    foods.put("Biscuit", foods.get("Biscuit") + 1);
                    break;
                case 50:
                    foods.put("Cake", foods.get("Cake") + 1);
                    break;
                case 75:
                    foods.put("Pastry", foods.get("Pastry") + 1);
                    break;
                case 100:
                    foods.put("Pie", foods.get("Pie") + 1);
                    break;
                default:
                    ingredient += 3;
                    ingredients.push(ingredient);
            }

        }

        if (foods.get("Biscuit") >= 1 && foods.get("Cake") >= 1 && foods.get("Pastry") >= 1 && foods.get("Pie") >= 0) {
            System.out.println("Great! You succeeded in cooking all the food!");
        } else {
            System.out.println("What a pity! You didn't have enough materials to cook everything.");
        }

        if (liquids.isEmpty()) {
            System.out.println("Liquids left: none");
        } else {
            List<String> join = new ArrayList<>();
            liquids.forEach(l -> join.add(String.valueOf(l)));
            System.out.println("Liquids left: " + String.join(", ", join));
        }

        if (ingredients.isEmpty()) {
            System.out.println("Ingredients left: none");
        } else {
            List<String> join = new ArrayList<>();
            ingredients.forEach(l -> join.add(String.valueOf(l)));
            System.out.println("Ingredients left: " + String.join(", ", join));
        }

        for (Map.Entry<String, Integer> entry : foods.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());

        }
    }
}
