package beforeCourse.lists_5.ex;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P01_Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> wagons = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int maxCapacity = Integer.parseInt(scanner.nextLine());
        String command = scanner.nextLine();

        while (!command.equals("end")) {
            if (command.split(" ").length > 1) {
                int countPassengers = Integer.parseInt(command.split(" ")[1]);
                wagons.add(countPassengers);

            } else {
                int passengersToIn = Integer.parseInt(command.split(" ")[0]);
                for (int i = 0; i < wagons.size(); i++) {
                    if (wagons.get(i) + passengersToIn <= maxCapacity) {
                        wagons.set(i, wagons.get(i) + passengersToIn);
                        break;
                    }
                }
            }
            command = scanner.nextLine();
        }
        for ( int passengers:wagons ){
            System.out.print(passengers+" ");
        }
    }
}
