package beforeCourse.textProcessing_8.ex;

import java.util.Scanner;

public class P01_ValidUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] text = scanner.nextLine().split(", ");
        for (int i = 0; i < text.length; i++) {
            String currentWord = text[i];
            boolean isValid = false;
            if (currentWord.length() >= 3 && currentWord.length() <= 16) {
                char[] wordArr = currentWord.toCharArray();
                for (int j = 0; j < wordArr.length; j++) {
                    char symbol = wordArr[j];
                    if (Character.isLetterOrDigit(wordArr[j]) || symbol == 45 || symbol == 95) {
                            isValid = true;
                    }else {
                        isValid=false;
                        break;
                    }
                }
            }
            if (isValid){
                System.out.println(currentWord);
            }
        }

    }
}
