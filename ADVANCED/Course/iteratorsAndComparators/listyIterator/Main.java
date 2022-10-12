package iteratorsAndComparators.comparingObjects.listyIterator;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        String[] createCommandArgs = Arrays.stream(command.split("\\s+")).skip(1).toArray(String[]::new);

        ListyIterator listyIterator = new ListyIterator();
        listyIterator.create(createCommandArgs);

        command = scanner.nextLine();
        while (!command.equals("END")){
            switch (command){
                case "Move":
                    System.out.println(listyIterator.move());
                    break;
                case "HasNext":
                    System.out.println(listyIterator.hasNext());
                    break;
                case "Print":
                    try {
                        listyIterator.print();
                    }catch (Exception e){
                        System.out.println("Invalid Operation!");
                    }
                    break;
                case "PrintAll":
                    listyIterator.printAll();
                    break;
            }

            command = scanner.nextLine();
        }

    }
}
