package FundamentalsCourse.midExamMid.exam_04;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P02_ShoppingList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> input = Arrays.stream(scanner.nextLine().split("!+")).collect(Collectors.toList());
        String command = scanner.nextLine();

        while (!command.equals("Go Shopping!")){
            String action = command.split(" ")[0];
            String item = command.split(" ")[1];

            switch (action){
                case "Urgent":
                    if (!input.contains(item)){
                        input.add(0,item);
                    }
                    break;
                case "Unnecessary":
                    if (input.contains(item)){
                        input.remove(input.indexOf(item));
                    }
                    break;
                case "Correct":
                    String newItem = command.split(" ")[2];
                    if (input.contains(item)){
                        input.add(input.indexOf(item), newItem);
                        input.remove(input.indexOf(newItem)+1);
                    }
                    break;
                case "Rearrange":
                    if (input.contains(item)){
                        input.remove(input.indexOf(item));
                        input.add(item);
                    }
                    break;
            }

            command= scanner.nextLine();
        }
        System.out.println(input.toString().replaceAll("[\\[\\]]",""));
    }
}
