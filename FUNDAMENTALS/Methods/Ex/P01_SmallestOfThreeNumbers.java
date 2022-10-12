package before.course.methods_4.ex;

import java.util.Scanner;

public class P01_SmallestOfThreeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOne = Integer.parseInt(scanner.nextLine());
        int numTwo = Integer.parseInt(scanner.nextLine());
        int numThree = Integer.parseInt(scanner.nextLine());
        smallestNumber(numOne,numTwo,numThree);
    }
    private static void smallestNumber (int a, int b, int c){
        if (a<=b && a<=c){
            System.out.println(a);
        }else if (b<=a && b<=c){
            System.out.println(b);
        }else {
            System.out.println(c);
        }
    }
}
