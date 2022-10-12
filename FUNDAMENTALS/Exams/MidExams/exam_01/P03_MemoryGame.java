package FundamentalsCourse.midExamMid.exam_01;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P03_MemoryGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> input = Arrays.stream(scanner.nextLine().split(" ")).collect(Collectors.toList());
        String command = scanner.nextLine();

        int countMoves = 0;

        while (!command.equals("end")) {
            int firstIndex = Integer.parseInt(command.split(" ")[0]);
            int secondIndex = Integer.parseInt(command.split(" ")[1]);
            countMoves++;
            int middleIndex = input.size() / 2;
            if (firstIndex == secondIndex || firstIndex < 0 || secondIndex > input.size() - 1 || secondIndex<0 || firstIndex>input.size()-1) {
                input.add(middleIndex, "-" + countMoves + "a");
                input.add(middleIndex + 1, "-" + countMoves + "a");
                System.out.println("Invalid input! Adding additional elements to the board");
            } else {
                if (input.get(firstIndex).equals(input.get(secondIndex))) {
                    System.out.printf("Congrats! You have found matching elements - %s!%n", input.get(firstIndex));
                    if (firstIndex<secondIndex){
                        input.remove(firstIndex);
                        input.remove(secondIndex-1);
                    }else {
                        input.remove(secondIndex);
                        input.remove(firstIndex-1);
                    }

                }else {
                    System.out.println("Try again!");
                }
            }
            if (input.size()==0){
                System.out.printf("You have won in %d turns!%n", countMoves);
                break;
            }

            command = scanner.nextLine();
        }
       if (input.size()>0){
           System.out.println("Sorry you lose :(");
           for (String element : input ){
               System.out.print(element+" ");
           }
       }
    }
}
