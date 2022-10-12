package FundamentalsCourse.finalExam.exam01;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P02_AdAstra {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String regex = "(?<sep>[#\\|]?)(?<food>[A-z\\s]+)(\\1)(?<date>\\d{2}\\/\\d{2}\\/\\d{2})(\\1)(?<calories>\\d+)(\\1)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int totalCalories = 0;
        List<List<String>> products = new ArrayList<>();

        while (matcher.find()){
            String food = matcher.group("food");
            String date = matcher.group("date");
            String calories =matcher.group("calories");
            products.add(List.of(food, date, calories));


            totalCalories+=Integer.parseInt(matcher.group("calories"));

        }
        System.out.printf("You have food to last you for: %d days!%n", totalCalories/2000);

        for (List<String> product : products) {
            System.out.printf("Item: %s, Best before: %s, Nutrition: %s%n", product.get(0), product.get(1), product.get(2));
        }
    }
}
