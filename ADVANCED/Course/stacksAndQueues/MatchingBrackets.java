package stacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class MatchingBrackets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        ArrayDeque<Integer> parts = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '('){
                parts.push(i);
            }else if (expression.charAt(i) == ')'){
                int startIndex = parts.pop();
                System.out.println(expression.substring(startIndex, i+1));
            }
        }
    }
}
