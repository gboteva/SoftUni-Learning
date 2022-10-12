package before.course.methods_4.ex;

import java.util.Scanner;

public class P09PalindromeIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("END")) {
            if (isPalindrome(command)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
            command = scanner.nextLine();
        }
    }

    private static boolean isPalindrome(String text) {
        String reversedString = "";
        for (int i = text.length() - 1; i >= 0; i--) {
            reversedString += text.charAt(i);
        }
        if (reversedString.equals(text)) {
            return true;
        } else {
            return false;
        }
    }
}
