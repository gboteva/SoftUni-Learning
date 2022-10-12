package beforeCourse.lists_5.ex;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P09_PokemonDontGo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> distanceList = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int index = Integer.parseInt(scanner.nextLine());
        int removedElement = 0;
        int sumOfRemovedElements = 0;
        while (!distanceList.isEmpty()) {
            if (index < 0) {
                removedElement = distanceList.get(0);
                distanceList.remove(0);
                sumOfRemovedElements += removedElement;
                int lastElement = distanceList.get(distanceList.size() - 1);
                distanceList.add(0, lastElement);
            } else if (index >= distanceList.size()) {
                removedElement = distanceList.get(distanceList.size() - 1);
                distanceList.remove(distanceList.size() - 1);
                sumOfRemovedElements += removedElement;
                int firstElement = distanceList.get(0);
                distanceList.add(firstElement);

            } else {
                removedElement = distanceList.get(index);
                distanceList.remove(index);
                sumOfRemovedElements += removedElement;
            }
            for (int i = 0; i < distanceList.size(); i++) {
                if (distanceList.get(i) <= removedElement) {
                    distanceList.set(i, removedElement + distanceList.get(i));
                } else {
                    distanceList.set(i, distanceList.get(i) - removedElement);
                }

            }
            if (distanceList.isEmpty()){
                break;
            }
            index = Integer.parseInt(scanner.nextLine());
        }
        System.out.println(sumOfRemovedElements);
    }
}
