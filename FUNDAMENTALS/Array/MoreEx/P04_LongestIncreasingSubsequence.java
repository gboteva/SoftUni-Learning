package FundamentalsCourse.arrays_3.moreEx;

import java.util.Arrays;
import java.util.Scanner;

public class P04_LongestIncreasingSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int [] numArr = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int maxLength = 0;
        int lastIndex = -1;
        int[] len = new int[numArr.length];
        int[] previous = new int[numArr.length];

        for (int i = 0; i < numArr.length; i++) {
            len[i] = 1;
            previous[i] = -1;

            for (int k = 0; k < i; k++) {
                if (numArr[k] < numArr[i] && len[k] + 1 > len[i]) {
                    len[i] = len[k] + 1;
                    previous[i] = k;
                }
            }

            if (len[i] > maxLength) {
                maxLength = len[i];
                lastIndex = i;
            }
        }

        int[] lis = new int[maxLength];
        int currentIndex = maxLength - 1;

        while (lastIndex != -1)  {
            lis[currentIndex] = numArr[lastIndex];
            currentIndex--;
            lastIndex = previous[lastIndex];
        }
        for(int print = 0; print < lis.length; print++){
            System.out.print(lis[print] + " ");
        }
    }
}
