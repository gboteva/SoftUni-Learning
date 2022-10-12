package FundamentalsCourse.arrays_3.arrays.ex;

import java.util.Arrays;
import java.util.Scanner;

public class P11_ArrayManipulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String command = scanner.nextLine();

        while (!command.equals("end")) {

            if (command.contains("exchange")) {
                String[] commandArray = command.split(" "); //14 13 12 11 17 16 15
                int index = Integer.parseInt(commandArray[1]);     // 11 17 16 15 14 13 12
                if (index > input.length - 1 || index < 0) {
                    System.out.println("Invalid index");
                } else {
                    exchangeArray(input, command);
                }
            } else if (command.contains("max")) {
                String result = maxEvenOrOddElement(input, command);
                System.out.println(result);
            } else if (command.contains("min")) {
                String result = minEvenOrOddElement(input, command);
                System.out.println(result);
            } else if (command.contains("first")) {
                String[] commandArray = command.split(" ");
                int count = Integer.parseInt(commandArray[1]);
                if (count > input.length) {
                    System.out.println("Invalid count");
                } else {
                    String result = firstCountEvenOrOdd(input, command);
                    System.out.print("[");
                    System.out.print(result);
                    System.out.println("]");
                }
            } else if (command.contains("last")) {
                String[] commandArray = command.split(" ");
                int count = Integer.parseInt(commandArray[1]);
                if (count > input.length) {
                    System.out.println("Invalid count");
                } else {
                    String result = lastCountEvenOrOdd(input, command);
                    System.out.print("[");
                    System.out.print(result);
                    System.out.println("]");
                }
            }
            command = scanner.nextLine();
        }
        System.out.print("[");
        for (int i = 0; i < input.length ; i++) {
            if (i< input.length-1){
                System.out.print(input[i]+", ");
            }else {
                System.out.print(input[i]+"]");
            }
        }
    }

    private static int[] exchangeArray(int[] input, String command) {
        String[] commandArray = command.split(" ");
        int index = Integer.parseInt(commandArray[1]);

        int[] first = new int[index + 1];
        int[] second = new int[input.length - (index + 1)];

        for (int i = 0; i < first.length; i++) {
            first[i] = input[i];
        }
        for (int i = 0; i < second.length; i++) {
            second[i] = input[index + 1 + i];
        }
        for (int i = 0; i < second.length; i++) {
            input[i] = second[i];
        }

        for (int i = 0; i <first.length ; i++) {
            input[second.length+i] = first[i];
        }

        return input;
    }

    private static String maxEvenOrOddElement(int[] input, String command) {
        String output = "";
        int indexElement = -1;
        if (command.contains("even")) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < input.length; i++) {
                if (input[i] % 2 == 0) {
                    if (input[i] >= max) {
                        max = input[i];
                        indexElement = i;
                    }
                }
            }
        } else if (command.contains("odd")) {
            int max = 0;
            for (int i = 0; i < input.length; i++) {
                if (input[i] % 2 != 0) {
                    if (input[i] >= max) {
                        max = input[i];
                        indexElement = i;
                    }
                }
            }
        }
        if (indexElement < 0) {
            output = "No matches";
        } else {
            output = "" + indexElement;
        }
        return output;
    }

    private static String minEvenOrOddElement(int[] input, String command) {
        String output = "";
        int indexElement = -1;
        if (command.contains("even")) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < input.length; i++) {
                if (input[i] % 2 == 0) {
                    if (input[i] <= min) {
                        min = input[i];
                        indexElement = i;
                    }
                }
            }
        } else if (command.contains("odd")) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < input.length; i++) {
                if (input[i] % 2 != 0) {
                    if (input[i] <= min) {
                        min = input[i];
                        indexElement = i;
                    }
                }
            }
        }
        if (indexElement < 0) {
            output = "No matches";
        } else {
            output = "" + indexElement;
        }
        return output;
    }


    private static String firstCountEvenOrOdd(int[] input, String command) {
        String[] commandArray = command.split(" ");
        int count = Integer.parseInt(commandArray[1]);
        String result = "";
        if (command.contains("even")) {
            int countEven = 0;
            String output = "";
            for (int i = 0; i < input.length; i++) {
                if (input[i] % 2 == 0 && countEven < count) {
                    countEven++;
                    output += input[i] + " ";
                }
            }
            String[] outputArray = output.split(" ");
            result = String.join(", ", outputArray);

        } else if (command.contains("odd")) {
            String output = "";
            int countOdd = 0;
            for (int i = 0; i < input.length; i++) {
                if (input[i] % 2 != 0 && countOdd < count) {
                    countOdd++;
                    output += input[i] + " ";
                }
            }
            String[] outputArray = output.split(" ");
            result = String.join(", ", outputArray);
        }

        return result;
    }

    private static String lastCountEvenOrOdd(int[] input, String command) {
        String[] commandArray = command.split(" ");
        int count = Integer.parseInt(commandArray[1]);
        String result = "";
        if (command.contains("even")) {
            int countEven = 0;
            String output = "";
            for (int i = input.length - 1; i > 0; i--) {
                if (input[i] % 2 == 0 && countEven < count) {
                    countEven++;
                    output += input[i] + " ";
                }
            }
            String[] outputArray = output.split(" ");
            for (int i = 0; i < outputArray.length / 2; i++) {
                String oldElement = outputArray[i];
                outputArray[i] = outputArray[outputArray.length - 1 - i];
                outputArray[outputArray.length - 1 - i] = oldElement;
            }
            result = String.join(", ", outputArray);

        } else if (command.contains("odd")) {
            String output = "";
            int countOdd = 0;
            for (int i = input.length - 1; i >= 0; i--) {
                if (input[i] % 2 != 0 && countOdd < count) {
                    countOdd++;
                    output += input[i] + " ";
                }
            }
            String[] outputArray = output.split(" ");
            for (int i = 0; i < outputArray.length / 2; i++) {
                String oldElement = outputArray[i];
                outputArray[i] = outputArray[outputArray.length - 1 - i];
                outputArray[outputArray.length - 1 - i] = oldElement;
            }
            result = String.join(", ", outputArray);
        }

        return result;
    }
}

