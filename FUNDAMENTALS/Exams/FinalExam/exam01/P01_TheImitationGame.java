package FundamentalsCourse.finalExam.exam01;

import java.beans.PropertyEditorManager;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P01_TheImitationGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        String command = scanner.nextLine();
        String decrypted = message;

        while (!command.equals("Decode")){
            String action = command.split("\\|+")[0];
            switch (action){
                case "Move":
                    int count = Integer.parseInt(command.split("\\|+")[1]);
                    decrypted = moveLetters(decrypted,count);
                    break;
                case "Insert":
                    int index = Integer.parseInt(command.split("\\|+")[1]);
                    String value = command.split("\\|+")[2];
                    decrypted = insertValue(decrypted, index,value);
                    break;
                case "ChangeAll":
                    String substring = command.split("\\|+")[1];
                    String replacement= command.split("\\|+")[2];
                    decrypted = decrypted.replace(substring,replacement);
                    break;
            }

            command=scanner.nextLine();
        }
        System.out.printf("The decrypted message is: %s", decrypted);
    }


    private static String insertValue(String decrypted, int index, String value) {
        String before = decrypted.substring(0,index);
        String after = decrypted.substring(index);
        StringBuilder newMessage = new StringBuilder();
        newMessage.append(before);
        newMessage.append(value);
        newMessage.append(after);
        return String.valueOf(newMessage);
    }

    private static String moveLetters(String decrypted, int count) {
        String letters = decrypted.substring(0,count);
        String others = decrypted.substring(count);
        StringBuilder newMessage = new StringBuilder();
        newMessage.append(others);
        newMessage.append(letters);
        return String.valueOf(newMessage);
    }
}
