package before.course.methods_4.m.ex;

import java.util.Scanner;

public class P01_DataTypes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();

        switch (type) {
            case "int":
                int number = Integer.parseInt(scanner.nextLine());
                System.out.println(printResult(number));
                break;
            case "real":
                double realNumber = Double.parseDouble(scanner.nextLine());
                System.out.printf("%.2f%n",printResult(realNumber));
                break;
            case "string":
                String input = scanner.nextLine();
                System.out.println(printResult(input));
                break;
        }
    }

    private static int printResult(int input) {
        return input * 2;
    }

    private static double printResult(double input) {
        return input*1.5;
    }

    private static String printResult (String input){
        return "$"+input+"$";
    }
}
