package implementations;

import interfaces.Solvable;

import java.util.ArrayDeque;

public class BalancedParentheses implements Solvable {
    private String parentheses;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    @Override
    public Boolean solve() {
        ArrayDeque<Character> openBrackets = new ArrayDeque<>();
        boolean isBalanced = false;
        for (int i = 0; i < parentheses.length(); i++) {
            char currentChar = parentheses.charAt(i);

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
        return isBalanced;
    }
}
