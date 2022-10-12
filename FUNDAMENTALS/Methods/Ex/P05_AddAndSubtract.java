package before.course.methods_4.ex;

import java.util.Scanner;

public class P05_AddAndSubtract {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int firstNum = Integer.parseInt(scanner.nextLine());
        int seconfNum = Integer.parseInt(scanner.nextLine());
        int thirdNum = Integer.parseInt(scanner.nextLine());

        int sum = getSumOfFirstTwoNumbers(firstNum,seconfNum);
        int subtract = getSubtractOfSumAndThirdNum(sum,thirdNum);
        System.out.println(subtract);
    }

    private static int getSumOfFirstTwoNumbers (int a, int b){
        return a+b;
    }

    private  static int getSubtractOfSumAndThirdNum (int sum, int c){
        return sum-c;
    }
}
