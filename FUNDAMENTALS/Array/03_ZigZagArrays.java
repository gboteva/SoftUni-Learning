package Array_Ex;

import java.util.Scanner;

public class ZigZagArrays_03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String [] evenArr = new String[n];
        String [] oddArr = new String[n];
        for (int i = 0; i <n; i++) {
            String [] input = scanner.nextLine().split(" ");
            String firstNum = input[0];
            String secondNum = input[1];
            if ((i+1)%2==0){
                evenArr[i] = firstNum;
                oddArr[i]=secondNum;
            }else {
                 oddArr[i]=firstNum;
                evenArr[i] = secondNum;
            }
        }
        for ( String element: oddArr ){
            System.out.print(element+" ");
        }
        System.out.println();
        for ( String element: evenArr ){
            System.out.print(element+" ");
        }
    }
}
