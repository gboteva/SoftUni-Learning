package FundamentalsCourse.arrays_3.moreEx;

import java.util.Scanner;

public class RecursiveFibonacci_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        //GetFibonacci(n) = GetFibonacci(n-1) + GetFibonacci (n-2)
        int [] fibonacci = new int[n];

        if (n>=1&&n<=50){
            for (int i = 0; i < n; i++) {
                if (i==0 || i==1){
                    fibonacci[i]=1;
                }else {
                    fibonacci[i]=fibonacci[i-2]+fibonacci[i-1];
                }
            }
            System.out.println(fibonacci[n-1]);
        }else {
            System.out.println(n);
        }

    }
}
