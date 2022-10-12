package Array_Ex;

import java.util.Arrays;
import java.util.Scanner;

public class MaxSequenceOfEqualElements_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int countEqualsEl = 1;
        int equalNum = 0;
        int maxSequence = 0;
        int maxNum = 0;

        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] == input[i + 1]) {
                equalNum = input[i];
                countEqualsEl++;
            } else {
                if (countEqualsEl>maxSequence){
                    maxSequence=countEqualsEl;
                    maxNum=equalNum;
                }
                countEqualsEl=1;
                equalNum=0;
            }
        }
        if (countEqualsEl>maxSequence){
            maxSequence=countEqualsEl;
            maxNum=equalNum;
        }
        int [] equalArray = new int[maxSequence];
        for (int index = 0; index < maxSequence; index++) {
            equalArray[index]= maxNum;
        }
        for ( int numbers:equalArray ){
            System.out.print(numbers+" ");
        }
    }
}
