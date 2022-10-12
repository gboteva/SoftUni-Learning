package StacksAndQueues_01.ex;

import java.util.ArrayDeque;
import java.util.Scanner;

public class P05_BalancedParentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ArrayDeque<Character> openBrackets = new ArrayDeque<>();
        boolean isBalanced = false;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar == '{' || currentChar == '[' || currentChar == '(') {
                openBrackets.push(currentChar);
            } else {
                if (openBrackets.isEmpty()) {
                    isBalanced = false;
                    break;
                }
                char lastOpen = openBrackets.pop();
                if (currentChar == '}' && lastOpen == '{') {
                    isBalanced = true;
                } else if (currentChar == ']' && lastOpen == '[') {
                    isBalanced = true;
                } else if (currentChar == ')' && lastOpen == '(') {
                    isBalanced = true;
                } else {
                    isBalanced = false;
                    break;
                }
            }
        }
        if (isBalanced) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

