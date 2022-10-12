package before.course.methods_4.ex;

import java.util.Scanner;

public class P07_NxNMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        printMatrixNxN(n);
    }

    private static void printMatrixNxN (int n){
        String row = "";
        for (int i = 0; i <n ; i++) {
           row +=n+ " ";
        }
        for (int i = 0; i <n ; i++) {
            System.out.println(row);
        }
    }
}
