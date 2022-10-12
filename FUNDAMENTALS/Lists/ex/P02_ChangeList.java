package beforeCourse.lists_5.ex;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P02_ChangeList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> input  = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        String command = scanner.nextLine();

        while (!command.equals("end")){
            if (command.contains("Delete")){
                int elementToDelete = Integer.parseInt(command.split(" ")[1]);
                input.removeAll(Arrays.asList(elementToDelete));

            }else if (command.contains("Insert")){
                int elementToInsert = Integer.parseInt(command.split(" ")[1]);
                int index = Integer.parseInt(command.split(" ")[2]);
                input.add(index,elementToInsert);
            }

            command= scanner.nextLine();
        }
        for ( int numbers : input ){
            System.out.print(numbers+" ");
        }
    }
}
