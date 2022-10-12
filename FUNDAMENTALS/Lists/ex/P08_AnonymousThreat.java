package beforeCourse.lists_5.ex;

import java.util.*;
import java.util.stream.Collectors;

public class P08_AnonymousThreat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> data = Arrays.stream(scanner.nextLine().split(" ")).collect(Collectors.toList());
        String command = scanner.nextLine();

        while (!command.equals("3:1")) {
            if (command.contains("merge")) {
                int startIndex = Integer.parseInt(command.split(" ")[1]);
                int endIndex = Integer.parseInt(command.split(" ")[2]);
                merge(data, startIndex, endIndex);

            } else if (command.contains("divide")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                int partitions = Integer.parseInt(command.split(" ")[2]);
                divide(data, index, partitions);
            }

            command = scanner.nextLine();
        }
        for (String item : data) {
            System.out.print(item + " ");
        }

    }

    private static List<String> merge(List<String> data, int startIndex, int endIndex) {
        if (startIndex < 0 || startIndex > data.size() - 1) {
            startIndex = 0;
        }
        if (endIndex > data.size() - 1) {
            endIndex = data.size() - 1;
        }

        String newWord = "";
        for (int i = startIndex; i <= endIndex; i++) {
            newWord += data.get(i);
        }

        data.removeAll(data.subList(startIndex, endIndex + 1));
        data.add(startIndex, newWord);
        return data;
    }

    private static List<String> divide(List<String> data, int index, int partitions) {
        String elementToDivide = data.get(index);

        if (elementToDivide.length() % partitions == 0) {
            int countSubElement = elementToDivide.length() / partitions;
            List<String> sublist = new ArrayList<>();
            int indexesForSublist = 0;
            String dividedWord = "";
            for (int j = 0; j < elementToDivide.length(); j++) {
                dividedWord+=elementToDivide.charAt(j);
                if (dividedWord.length()==countSubElement){
                    sublist.add(indexesForSublist, dividedWord);
                    dividedWord="";
                    indexesForSublist++;
                }
            }
            data.remove(index);
            data.addAll(index,sublist);

        } else {
            int equalsPartitions = partitions - 1;
            int countSubElementOfEqualsPart = (elementToDivide.length() - 1) / equalsPartitions;
            int countSubElementOfLastPart = elementToDivide.length() - (equalsPartitions * countSubElementOfEqualsPart);

            List<String> sublist = new ArrayList<>();
            int indexesForSublist = 0;
            String dividedWord = "";
            for (int j = 0; j < elementToDivide.length() - countSubElementOfLastPart; j++) {    //взимаме цялата дължина на думата без последните елементи
                dividedWord += elementToDivide.charAt(j);         //добавяме всяка буква към стринг
                if (dividedWord.length() == countSubElementOfEqualsPart) {        //проверяваме дали броя им е колкото е изчисления за еднаквите части
                    sublist.add(indexesForSublist, dividedWord);            //добавяме ги към сублиста
                    dividedWord = "";                                     //нулираме стринга
                    indexesForSublist++;                                //увеличаваме индекса за сублиста
                }

            }
            for (int i = elementToDivide.length() - countSubElementOfLastPart; i < elementToDivide.length(); i++) { //същото за последния най-голям елемент
                dividedWord += elementToDivide.charAt(i);
                if (dividedWord.length() == countSubElementOfLastPart) {
                    sublist.add(indexesForSublist, dividedWord);
                }
            }
            data.remove(index);
            data.addAll(index,sublist);
        }
        return data;
    }
}
