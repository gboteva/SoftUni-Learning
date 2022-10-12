package beforeCourse.lists_5.moreEx;

import java.util.*;
import java.util.stream.Collectors;

public class P04_MixedUpLists {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> firstLine = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> secondLine = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> resultList = new ArrayList<>();
        Collections.reverse(secondLine);
        int minSize = Math.min(firstLine.size(), secondLine.size());
        for (int i = 0; i < minSize; i++) {
            resultList.add(firstLine.get(i));
            resultList.add(secondLine.get(i));
        }
        List<Integer> remainingElements = new ArrayList<>();
        if (firstLine.size() > secondLine.size()) {
            for (int i = minSize; i < firstLine.size(); i++) {
                remainingElements.add(firstLine.get(i));
            }
        } else {
            for (int i = minSize; i < secondLine.size(); i++) {
                remainingElements.add(secondLine.get(i));
            }
        }
        List<Integer> printList = new ArrayList<>();
        if (remainingElements.get(0) > remainingElements.get(1)) {
            for (int i = 0; i < resultList.size(); i++) {
                if (resultList.get(i) < remainingElements.get(0) && resultList.get(i) > remainingElements.get(1)) {
                    printList.add(resultList.get(i));
                }
            }
        } else {
            for (int i = 0; i < resultList.size(); i++) {
                if (resultList.get(i) > remainingElements.get(0) && resultList.get(i) < remainingElements.get(1)) {
                    printList.add(resultList.get(i));
                }
            }
        }
        Collections.sort(printList);
        for ( int element: printList ){
            System.out.print(element+" ");
        }
    }
}
