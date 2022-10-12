package FundamentalsCourse.midExamMid.exam_04;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P03_HeartDelivery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> input = Arrays.stream(scanner.nextLine().split("@")).map(Integer::parseInt).collect(Collectors.toList());
        String command = scanner.nextLine();
        int position = 0;
        while (!command.equals("Love!")) {
            int lenght = Integer.parseInt(command.split(" ")[1]);

            if (position + lenght >= 0 && position + lenght < input.size()) {
                position += lenght;
                if (input.get(position) == 0) {
                    System.out.printf("Place %d already had Valentine's day.%n", position);
                    command= scanner.nextLine();
                    continue;
                }
                input.add(position, input.get(position) - 2);
                input.remove(position + 1);

                if (input.get(position) == 0) {
                    System.out.printf("Place %d has Valentine's day.%n", position);
                }
            } else {
                position = -lenght;
                continue;
            }


            command = scanner.nextLine();
        }
        int sum = 0;
        int countFails = 0;
        for (int i = 0; i < input.size(); i++) {
            sum += input.get(i);
            if (input.get(i) > 0) {
                countFails++;
            }
        }

        System.out.printf("Cupid's last position was %d.%n", position);

        if (sum == 0) {
            System.out.printf("Mission was successful.%n");
        } else {
            System.out.printf("Cupid has failed %d places.%n", countFails);
        }

    }
}
