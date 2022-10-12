package FundamentalsCourse.midExamMid.exam_02;

import java.util.*;
import java.util.stream.Collectors;

public class P03_Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> input = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            sum += input.get(i);
        }
        double averageValue = sum * 1.0 / input.size();


        List<Integer> resultList = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) > averageValue) {
                resultList.add(index, input.get(i));
                index++;
            }
        }
        Collections.sort(resultList);
        Collections.reverse(resultList);
        if (resultList.size()>0) {
            for (int i = 0; i <resultList.size() ; i++) {
                if (i<5){
                    System.out.print(resultList.get(i) + " ");
                }
            }
        } else {
            System.out.println("No");
        }
    }
}
