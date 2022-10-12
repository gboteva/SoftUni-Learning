package FundamentalsCourse.midExamMid.exam_03;

import java.util.Arrays;
import java.util.Scanner;

public class P03_ShootForTheWin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] targets = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String command = scanner.nextLine();
        int counter = 0;

        while (!command.equals("End")) {
            int index = Integer.parseInt(command);
            if (index >= 0 && index <= targets.length - 1) {
                int value = 0;
                if (targets[index] != -1) {
                    value = targets[index];
                    targets[index] = -1;
                    counter++;

                    for (int i = 0; i < targets.length; i++) {
                        if (targets[i] != -1) {
                            if (targets[i] > value ) {
                                targets[i] -= value;
                            } else if (targets[i] <= value ) {
                                targets[i] += value;
                            }
                        }
                    }
                }
            }

            command = scanner.nextLine();
        }
        System.out.printf("Shot targets: %d -> ", counter);
        for (int element : targets ){
            System.out.print(element+" ");
        }
    }
}
