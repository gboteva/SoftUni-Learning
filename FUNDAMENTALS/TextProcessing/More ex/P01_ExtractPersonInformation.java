package FundamentalsCourse.textProcessing_8.moreEx;

import java.util.Scanner;

public class P01_ExtractPersonInformation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i <n ; i++) {
            String text = scanner.nextLine();
            String name = "";
            String age = "";
            int indexA = text.indexOf('@');
            int indexLine = text.indexOf('|');
            name = text.substring(indexA+1, indexLine);

            int indexSharp = text.indexOf('#');
            int indexStar = text.indexOf('*');
            age = text.substring(indexSharp+1, indexStar);

            System.out.printf("%s is %s years old.%n", name, age);
        }
    }
}
