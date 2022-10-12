package before.course.methods_4.ex;

import java.util.Scanner;

public class P08FactorialDivision {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOne = Integer.parseInt(scanner.nextLine());
        int numTwo = Integer.parseInt(scanner.nextLine());

        double result = factorial(numOne) *1.0/ factorial(numTwo);
        System.out.printf("%.2f", result);

    }
    private static long factorial (int a){
        long factorial = 1;
        for (int i = 1; i <= a; i++) {
            factorial*=i;
        }
        return factorial;
    }
}
