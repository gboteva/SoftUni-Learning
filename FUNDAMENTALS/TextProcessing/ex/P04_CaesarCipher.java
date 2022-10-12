package beforeCourse.textProcessing_8.ex;

import java.util.Scanner;

public class P04_CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i <text.length() ; i++) {
            int ascii = text.charAt(i)+3;
            char symbol =(char) ascii;
            encrypted.append(symbol);
        }
        System.out.println(encrypted.toString());
    }
}
