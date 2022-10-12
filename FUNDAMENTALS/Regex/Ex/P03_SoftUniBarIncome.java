package FundamentalsCourse.regex_9.ex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P03_SoftUniBarIncome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String regex = "%(?<name>[A-Z][a-z]+)%[^\\|\\$%\\.]*<(?<product>\\w+)>[^\\|\\$%\\.]*\\|(?<count>\\d+)\\|[^\\|\\$%\\.\\d]*(?<price>\\d+\\.?\\d+)\\$";
        Pattern pattern = Pattern.compile(regex);
        double total = 0;

        while (!input.equals("end of shift")){
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()){
            String name = matcher.group("name");
            String product = matcher.group("product");
            int count = Integer.parseInt(matcher.group("count"));
            double price = Double.parseDouble(matcher.group("price"));
            System.out.printf("%s: %s - %.2f%n", name, product, price*count);
            total+=(price*count);
        }
            input=scanner.nextLine();
        }
        System.out.printf("Total income: %.2f", total);
    }
}
