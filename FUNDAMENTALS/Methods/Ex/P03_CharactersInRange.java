package before.course.methods_4.ex;

import java.util.Scanner;

public class P03_CharactersInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char firstSymbol = scanner.nextLine().charAt(0);
        char secondSymbol = scanner.nextLine().charAt(0);

        lineOfCharactersBetwenTwo(firstSymbol,secondSymbol);

    }

    private static void lineOfCharactersBetwenTwo(char a, char b) {

        if (a < b) {
            for (int i = a + 1; i < b; i++) {
                System.out.print((char)i + " ");
            }
        } else {
            for (int i = b + 1; i < a; i++) {
                System.out.print((char)i + " ");
            }
        }
    }

}
