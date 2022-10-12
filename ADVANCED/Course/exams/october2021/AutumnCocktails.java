package exams.october2021;

import java.util.*;

public class AutumnCocktails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> ingredients = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(ingredients::offer);

        ArrayDeque<Integer> freshness = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(freshness::push);

        Map<String, Integer> cocktails = new TreeMap<>();
        cocktails.put("Pear Sour", 0);
        cocktails.put("The Harvest", 0);
        cocktails.put("Apple Hinny", 0);
        cocktails.put("High Fashion", 0);

        while (!ingredients.isEmpty() && !freshness.isEmpty()) {
            int ingredient = ingredients.poll();
            int fresh = freshness.pop();
            if (ingredient == 0) {
                freshness.push(fresh);
                continue;
            }
            int result = ingredient * fresh;

            if (result == 150) {
                cocktails.put("Pear Sour", cocktails.get("Pear Sour") + 1);
            } else if (result == 250) {
                cocktails.put("The Harvest", cocktails.get("The Harvest") + 1);
            } else if (result == 300) {
                cocktails.put("Apple Hinny", cocktails.get("Apple Hinny") + 1);
            } else if (result == 400) {
                cocktails.put("High Fashion", cocktails.get("High Fashion") + 1);
            } else {
                ingredient += 5;
                ingredients.offerLast(ingredient);
            }

        }

        if (haveIAtLeastOne(cocktails)) {
            System.out.println("It's party time! The cocktails are ready!");
        } else {
            System.out.println("What a pity! You didn't manage to prepare all cocktails.");
        }

        if (!ingredients.isEmpty()) {
            int sum = ingredients.stream().mapToInt(e -> e).sum();
            System.out.println("Ingredients left: " + sum);
        }

        for (Map.Entry<String, Integer> entry : cocktails.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println(" # " + entry.getKey() + " --> " + entry.getValue());
            }
        }

    }

    private static boolean haveIAtLeastOne(Map<String, Integer> cocktails) {
        return cocktails.get("Pear Sour") > 0 && cocktails.get("The Harvest") > 0
                && cocktails.get("Apple Hinny") > 0 && cocktails.get("High Fashion") > 0;
    }
}
