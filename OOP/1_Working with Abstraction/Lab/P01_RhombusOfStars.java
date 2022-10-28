package Lab;

import java.util.Scanner;

public class P01_RhombusOfStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int firstHalfRow = 1; firstHalfRow <= n; firstHalfRow++) {
           printRow(n, firstHalfRow);
        }

        for (int secHalfRow = n-1; secHalfRow >=1 ; secHalfRow--) {
            printRow(n, secHalfRow);
        }
    }

    private static void printRow(int figureSize, int starCount) {
        for (int i = 0; i < figureSize- starCount; i++) {
            System.out.print(" "); //принтира спейсовете до 1вата звезда
        }
        for (int star = 1; star < starCount; star++) {
            System.out.print("* "); //принтира звездите
        }
        System.out.println("*");
    }
}
