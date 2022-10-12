package FundamentalsCourse.regex_9.ex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P01_Furniture {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String forniture = scanner.nextLine();
        double totalSum = 0;
        List<String> fornitureName =  new ArrayList<>();
        while (!forniture.equals("Purchase")){
            String regex = ">{2}(?<name>[A-Za-z]+)<{2}(?<price>[\\d+\\.\\d+]+)!(?<quantity>\\d+)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(forniture);
            while (matcher.find()){
                String name = matcher.group("name");
                fornitureName.add(name);
                double price = Double.parseDouble(matcher.group("price"));
                int quantity = Integer.parseInt(matcher.group("quantity"));
                totalSum=totalSum+(price*quantity);
            }

            forniture= scanner.nextLine();
        }
        System.out.println("Bought furniture:");
        for (String name : fornitureName ){
            System.out.println(name);
        }
        System.out.printf("Total money spend: %.2f", totalSum);
    }
}
