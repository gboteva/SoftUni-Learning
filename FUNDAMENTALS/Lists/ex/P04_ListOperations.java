package beforeCourse.lists_5.ex;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P04_ListOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> inputList = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        String command = scanner.nextLine();
        while (!command.equals("End")){
            if (command.contains("Add")){
                int numberToAdd = Integer.parseInt(command.split(" ")[1]);
                inputList.add(numberToAdd);
            }else if (command.contains("Insert")){
                int numberToInsert = Integer.parseInt(command.split(" ")[1]);
                int index = Integer.parseInt(command.split(" ")[2]);
                if (index<0 || index>inputList.size()-1){
                    System.out.println("Invalid index");
                }else {
                    inputList.add(index,numberToInsert);
                }

            }else if (command.contains("Remove")){
                int index = Integer.parseInt(command.split(" ")[1]);
                if (index<0 || index>inputList.size()-1){
                    System.out.println("Invalid index");
                }else {
                    inputList.remove(index);
                }

            }else if (command.contains("Shift left")){
                int count = Integer.parseInt(command.split(" ")[2]);
                for (int i = 0; i <count ; i++) {
                    inputList.add(inputList.get(0));
                    inputList.remove(0);
                                    }
            }else if (command.contains("Shift right")){
                int count = Integer.parseInt(command.split(" ")[2]);
                for (int i = 0; i <count ; i++) {
                    int lastElement = inputList.get(inputList.size()-1);
                    inputList.add(0, lastElement);
                    inputList.remove(inputList.size()-1);
                }
            }

            command= scanner.nextLine();
        }
        for ( int numbers: inputList){
            System.out.print(numbers+" ");
        }
    }
}
