package FundamentalsCourse.finalExam.exam05;

import java.util.Scanner;

public class P01_ActivationKeys {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String activationKey = scanner.nextLine();
        String input = scanner.nextLine();

        while (!input.equals("Generate")){
            String[] command = input.split(">>>");
            String action = command[0];

            switch (action){
                case "Contains":
                    String substring = command[1];
                    if (activationKey.contains(substring)){
                        System.out.printf("%s contains %s%n", activationKey, substring);
                    } else {
                        System.out.println("Substring not found!");
                    }
                    break;
                case "Flip":
                    String upperOrLower = command[1];
                    int startIndex = Integer.parseInt(command[2]);
                    int endIndex = Integer.parseInt(command[3]);
                    String firstHalf = activationKey.substring(0, startIndex);
                    String secondHalf = activationKey.substring(endIndex);
                    StringBuilder changedKey = new StringBuilder();
                    changedKey.append(firstHalf);
                     if (upperOrLower.equals("Upper")){
                         String changed = activationKey.substring(startIndex,endIndex).toUpperCase();
                         changedKey.append(changed);
                         changedKey.append(secondHalf);
                         activationKey = String.valueOf(changedKey);
                         System.out.println(activationKey);
                     }else {
                         String changed = activationKey.substring(startIndex,endIndex).toLowerCase();
                         changedKey.append(changed);
                         changedKey.append(secondHalf);
                         activationKey = String.valueOf(changedKey);
                         System.out.println(activationKey);
                     }

                    break;
                case "Slice":
                    int start = Integer.parseInt(command[1]);
                    int end = Integer.parseInt(command[2]);
                    String toRemove= activationKey.substring(start, end);
                    activationKey= activationKey.replaceAll(toRemove,"");
                    System.out.println(activationKey);
                    break;
            }


            input= scanner.nextLine();
        }

        System.out.printf("Your activation key is: %s", activationKey);
    }
}
