package FundamentalsCourse.midExamMid.exam_05;

import java.util.Scanner;

public class P02_MuOnline {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\|");

        int health = 100;
        int bitcoins = 0;

        for (int i = 0; i < input.length; i++) {
            String command = input[i].split(" ")[0];
            int number = Integer.parseInt(input[i].split(" ")[1]);

            switch (command) {
                case "potion":
                    health += number;    //90 + 30 = 120
                    if (health > 100) {
                        number= 100-(health-number);//10
                        health = 100;
                    }
                    System.out.printf("You healed for %d hp.%n", number);
                    System.out.printf("Current health: %d hp.%n", health);
                    break;
                case "chest":
                    bitcoins += number;
                    System.out.printf("You found %d bitcoins.%n", number);
                    break;
                default:
                    health -= number;
                    if (health > 0) {
                        System.out.printf("You slayed %s.%n", command);
                    } else {
                        System.out.printf("You died! Killed by %s.%n", command);
                        System.out.printf("Best room: %d", i + 1);
                    }
                    break;
            }
            if (health <= 0) {
                break;
            }
        }
        if (health > 0) {
            System.out.println("You've made it!");
            System.out.println("Bitcoins: " + bitcoins);
            System.out.println("Health: " + health);
        }

    }
}
