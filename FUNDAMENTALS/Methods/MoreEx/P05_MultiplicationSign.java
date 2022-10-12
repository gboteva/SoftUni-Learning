package before.course.methods_4.m.ex;

import java.util.Scanner;

public class P05_MultiplicationSign {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double num1 = Double.parseDouble(scanner.nextLine());
        double num2 = Double.parseDouble(scanner.nextLine());
        double num3 = Double.parseDouble(scanner.nextLine());
        printSignOfMultiplication (num1,num2,num3);
    }

    private static void printSignOfMultiplication (double num1, double num2, double num3 ){
        int countMinus = 0;
        if (num1<0){
            countMinus++;
        }
        if (num2<0){
            countMinus++;
        }
        if (num3<0){
            countMinus++;
        }
        if (num1==0 || num2==0 || num3==0){
            System.out.println("zero");
        }else {
            if (countMinus%2==0){
                System.out.println("positive");
            }else {
                System.out.println("negative");
            }
        }
    }
}
