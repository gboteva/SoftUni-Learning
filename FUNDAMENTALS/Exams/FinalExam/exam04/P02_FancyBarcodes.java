package FundamentalsCourse.finalExam.exam04;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P02_FancyBarcodes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String regex = "@#+(?<barcode>[A-Z]{1}[A-Za-z0-9]{4,}[A-Z]{1})@#+";
        Pattern pattern = Pattern.compile(regex);


        for (int i = 0; i <n ; i++) {
            String string = scanner.nextLine();
            Matcher matcher = pattern.matcher(string);

            if (matcher.find()){
                StringBuilder productGroup = new StringBuilder();
                String barcode = matcher.group("barcode");
                for (int j = 0; j < barcode.length(); j++) {
                    if (Character.isDigit(barcode.charAt(j))){
                        productGroup.append(barcode.charAt(j));
                    }
                }
                String group = String.valueOf(productGroup);
                if (!group.equals("")){
                    System.out.println("Product group: " + group);
                }else {
                    System.out.println("Product group: 00");
                }
            }else {
                System.out.println("Invalid barcode");
            }
        }
    }
}
