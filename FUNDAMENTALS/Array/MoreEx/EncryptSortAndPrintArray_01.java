package FundamentalsCourse.arrays_3.arrays.m.Ex;

import java.util.Arrays;
import java.util.Scanner;

public class EncryptSortAndPrintArray_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countInput = Integer.parseInt(scanner.nextLine());

        int[] sums = new int[countInput];

        for (int i = 0; i < countInput; i++) {
            String input = scanner.nextLine();

            int sum = 0;
            for (int j = 0; j < input.length(); j++) {
                char letter = input.charAt(j);
                if (letter == 'a' || letter == 'e' || letter == 'o' || letter == 'u' || letter == 'i' ||
                        letter == 'A' || letter == 'E' || letter == 'O'|| letter == 'U' || letter == 'I') {
                    sum += input.length() * (int) letter;
                } else {
                    sum = sum + (int) letter / input.length();
                }
            }

            sums[i] = sum;
        }
        Arrays.sort(sums);              //сортира числови елементите  във възходящ ред.

        for (int sum : sums) {
            System.out.println(sum);
        }
    }
}

//((letter >= 'B' && letter <= 'Z') || ((letter>= 'b' && letter <= 'z')))
//за да даде макс точки, не трябва да се обхващат само букви от аскии таблицата, т.е. този ред не трябва да го има!