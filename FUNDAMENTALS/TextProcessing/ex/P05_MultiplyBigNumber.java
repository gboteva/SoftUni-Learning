package beforeCourse.textProcessing_8.ex;

import java.math.BigInteger;
import java.util.Scanner;

public class P05_MultiplyBigNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String bigNum = scanner.nextLine();

        int digit = Integer.parseInt(scanner.nextLine());

        StringBuilder result = new StringBuilder();

        int diff = 0;
        for (int i = bigNum.length() - 1; i >= 0; i--) {
            int currentDigit = Integer.parseInt(String.valueOf(bigNum.charAt(i)));
            int res = digit * currentDigit + diff;
            diff = 0;
            if (res >= 10) {
                result.append(res % 10);
                diff = res / 10;
            } else {
                result.append(res);
            }
        }

        if (diff != 0) {
            result.append(diff);
        }

        result.reverse();
        while (result.charAt(0) == '0' && result.length() > 1) {
            result.deleteCharAt(0);
        }
        System.out.println(result);

    }
}
