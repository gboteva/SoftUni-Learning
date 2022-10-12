package FundamentalsCourse.midExamMid.exam_02;

import java.util.Arrays;
import java.util.Scanner;

public class P03_ArrayModifier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String command = scanner.nextLine();

        while (!command.equals("end")) {
            String action = command.split(" ")[0];
            switch (action) {
                case "swap":
                    int indexOne = Integer.parseInt(command.split(" ")[1]);
                    int indexTwo = Integer.parseInt(command.split(" ")[2]);
                    int firstElement = input[indexOne];
                    int secondElement = input[indexTwo];
                    input[indexOne] = secondElement;
                    input[indexTwo] = firstElement;
                    break;
                case "multiply":
                    int indexOneMulty = Integer.parseInt(command.split(" ")[1]);
                    int indexTwoMulty = Integer.parseInt(command.split(" ")[2]);
                    int result = input[indexOneMulty] * input[indexTwoMulty];
                    input[indexOneMulty] = result;
                    break;
                case "decrease":
                    for (int i = 0; i < input.length ; i++) {
                        input[i]=input[i]-1;
                    }
                    break;
            }

            command = scanner.nextLine();
        }
        for (int i = 0; i <input.length ; i++) {
            if (i< input.length-1){
                System.out.print(input[i]+", ");
            }else {
                System.out.print(input[i]);
            }
        }

    }
}
