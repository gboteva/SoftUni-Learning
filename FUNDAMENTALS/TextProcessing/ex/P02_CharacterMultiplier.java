package beforeCourse.textProcessing_8.ex;

import java.util.Scanner;

public class P02_CharacterMultiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split("\\s+");
        int minLenght = Math.min(words[0].length(), words[1].length());
        int totalSum = 0;
        for (int i = 0; i <minLenght ; i++) {
            totalSum += words[0].charAt(i) * words[1].charAt(i);
        }
        if (words[0].length()>words[1].length()){
            for (int i = minLenght; i <words[0].length() ; i++) {
                totalSum+=words[0].charAt(i);
            }
        }else if (words[1].length()>words[0].length()) {
            for (int i = minLenght; i <words[1].length() ; i++) {
                totalSum+=words[1].charAt(i);
            }
        }
        System.out.println(totalSum);
    }
}
