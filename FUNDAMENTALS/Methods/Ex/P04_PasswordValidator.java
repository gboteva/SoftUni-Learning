package before.course.methods_4.ex;

import java.util.Scanner;

public class P04_PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        boolean isValidLength = isValidLength(text);
        boolean isValidContent = isValidContent(text);
        boolean isValidCountDigit = isValidCountDigit(text);

        if (!isValidLength){
            System.out.println("Password must be between 6 and 10 characters");
        }
        if (!isValidContent){
            System.out.println("Password must consist only of letters and digits");
        }
        if (!isValidCountDigit){
            System.out.println("Password must have at least 2 digits");
        }

        if (isValidLength && isValidContent && isValidCountDigit){
            System.out.println("Password is valid");
        }
    }

    private static boolean isValidLength(String text) {
        return text.length() >= 6 && text.length() <= 10;
    }

    private static boolean isValidContent(String text) {
        for (int i = 0; i < text.length(); i++) {
            char currentSymbol = text.charAt(i);
            if (!Character.isLetterOrDigit(currentSymbol)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidCountDigit (String text){
        int counter = 0;
        for (int i = 0; i <text.length() ; i++) {
            char currentSymbol = text.charAt(i);
            if (Character.isDigit(currentSymbol)){
                counter++;
            }
        }
        if (counter>=2){
            return true;
        }else {
            return false;
        }
    }
}
