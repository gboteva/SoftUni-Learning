package FundamentalsCourse.midExamMid.exam_05;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P03_Inventory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> input = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());
        String command = scanner.nextLine();

        while (!command.equals("Craft!")) {
            String action = command.split(" - ")[0];
            String item = command.split(" - ")[1];

            switch (action) {
                case "Collect":
                    if (!input.contains(item)) {
                        input.add(item);
                    }
                    break;
                case "Drop":
                    if (input.contains(item)) {
                        input.remove(item);
                    }
                    break;
                case "Combine Items":
                    item = (command.split(" - ")[1]).split(":")[0];
                    String newItem = (command.split(" - ")[1]).split(":")[1];
                    if (input.contains(item)){
                        input.add(input.indexOf(item)+1, newItem);
                    }
                    break;
                case "Renew":
                    if (input.contains(item)){
                        input.remove(input.indexOf(item));
                        input.add(item);
                    }
                    break;
            }

            command = scanner.nextLine();
        }
        System.out.println(input.toString().replaceAll("[\\[\\]]",""));
    }
}
