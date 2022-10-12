package FundamentalsCourse.finalExam.exam03;

import java.util.Scanner;

public class P01_SecretChar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        String command = scanner.nextLine();
        while (!command.equals("Reveal")) {
            String action = command.split(":\\|:")[0];
            switch (action) {
                case "InsertSpace":
                    int index = Integer.parseInt(command.split(":\\|:")[1]);
                    String firstHalf = message.substring(0, index);
                    String secondHalf = message.substring(index);
                    message = firstHalf + " " + secondHalf;
                    System.out.println(message);
                    break;
                case "Reverse":
                    String substring = command.split(":\\|:")[1];
                    if (message.contains(substring)) {
                        int startIndex = message.indexOf(substring);
                        int endIndex = startIndex + substring.length() - 1;
                        String first = message.substring(0, startIndex);
                        String second = message.substring(endIndex + 1);
                        String toReverse = message.substring(startIndex, endIndex + 1);
                        String reversedSubstring = getReversedStrin(toReverse);
                        StringBuilder reverse = new StringBuilder();
                        reverse.append(first);
                        reverse.append(second);
                        reverse.append(reversedSubstring);
                        message = String.valueOf(reverse);
                        System.out.println(message);
                    } else {
                        System.out.println("error");
                    }
                    break;
                case "ChangeAll":
                    String substringToChange = command.split(":\\|:")[1];
                    String replacement = command.split(":\\|:")[2];
                    message = message.replace(substringToChange, replacement);
                    System.out.println(message);
                    break;
            }

            command = scanner.nextLine();
        }
        System.out.println("You have a new text message: " + message);
    }

    private static String getReversedStrin(String toReverse) {
        StringBuilder reversedMessage = new StringBuilder();
        for (int i = toReverse.length() - 1; i >= 0; i--) {
            reversedMessage.append(toReverse.charAt(i));
        }
        return String.valueOf(reversedMessage);
    }
}
