package before.course.methods_4.ex;

import java.util.Scanner;

public class P02_VowelsCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine().toLowerCase();
        System.out.println(vowelsCounterInString(text));
    }

    private static int vowelsCounterInString(String text) {
        int countVowelsWord = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == 'a' || text.charAt(i) == 'e' || text.charAt(i) == 'o' ||
                    text.charAt(i) == 'i' || text.charAt(i) == 'u') {
                countVowelsWord++;
            }
        }
        return countVowelsWord;
    }
}
