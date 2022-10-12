package before.course.methods_4.ex;

import java.util.Scanner;

public class P10_TopNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        getTopNumber(num);

    }
    private static void getTopNumber (int n){
        for (int i = 1; i <=n ; i++) {
            int sum = 0;
            int countOddDigit = 0;
            int num = i;
            while (num>0){
                int currentDigit = num%10;
                sum+=currentDigit;
                if (currentDigit%2!=0){
                    countOddDigit++;
                }
                num/=10;
            }

            if (sum%8==0 && countOddDigit>=1){
                System.out.println(i);
            }

        }
    }
}
