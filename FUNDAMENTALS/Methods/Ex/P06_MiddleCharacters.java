package before.course.methods_4.ex;

import java.util.Scanner;

public class P06_MiddleCharacters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        printMiddleCharacters(input);
    }

    private static void printMiddleCharacters(String text) {
        if (text.length() % 2 != 0) {
            System.out.println(text.charAt(text.length() / 2));
        } else {
            System.out.print(text.charAt(text.length() / 2 - 1));
            System.out.print(text.charAt(text.length() / 2));
        }
    }
}
