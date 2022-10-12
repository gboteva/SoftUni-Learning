package FundamentalsCourse.midExamMid.exam_03;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P03_MovingTarget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> targets = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        String command = scanner.nextLine();


        while (!command.equals("End")) {
            String action = command.split(" ")[0];
            int index = Integer.parseInt(command.split(" ")[1]);

            switch (action) {
                case "Shoot":
                    int power = Integer.parseInt(command.split(" ")[2]);
                    if (index >= 0 && index <= targets.size() - 1) {
                        targets.add(index, targets.get(index) - power);
                        targets.remove(index + 1);
                        if (targets.get(index) <= 0) {
                            targets.remove(index);
                        }
                    }
                    break;
                case "Add":
                    int value = Integer.parseInt(command.split(" ")[2]);
                    if (index >= 0 && index <= targets.size() - 1) {
                        targets.add(index, value);
                    } else {
                        System.out.println("Invalid placement!");
                    }
                    break;
                case "Strike":
                    int radius = Integer.parseInt(command.split(" ")[2]);
                    if (index - radius >= 0 && index + radius <= targets.size() - 1) {
                        targets.subList(index - radius, index + radius + 1).clear();
                    } else {
                        System.out.println("Strike missed!");
                    }
                    break;
            }

            command = scanner.nextLine();
        }
        for (int i = 0; i < targets.size(); i++) {
            if (i < targets.size() - 1) {
                System.out.print(targets.get(i) + "|");
            } else {
                System.out.print(targets.get(i));
            }
        }

    }
}
