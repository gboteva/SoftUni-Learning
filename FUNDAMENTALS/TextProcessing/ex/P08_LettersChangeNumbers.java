package beforeCourse.textProcessing_8.ex;

import javax.xml.stream.events.Characters;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

public class P08_LettersChangeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input  = scanner.nextLine().split("\\s+");
        double multyplyOrDivide = 0;
        double resultNum = 0;
        double sum = 0;

        for (int i = 0; i < input.length; i++) {
            String word = input[i];
            char first = word.charAt(0);
            char last = word.charAt(word.length()-1);

            word = word.substring(1, word.length()-1);
            double number = Double.parseDouble(word);
            if (Character.isUpperCase(first)){
                int positionFirst = first-64;
                multyplyOrDivide = number/positionFirst;
            }else {
                int positionFirst = first-96;
                multyplyOrDivide=number*positionFirst;
            }

            if (Character.isUpperCase(last)){
                int positionLast = last-64;
                resultNum=multyplyOrDivide-positionLast;
            }else {
                int positionLast = last-96;
                resultNum=multyplyOrDivide+positionLast;
            }
            sum+=resultNum;
        }
        System.out.printf("%.2f", sum);
    }
}
