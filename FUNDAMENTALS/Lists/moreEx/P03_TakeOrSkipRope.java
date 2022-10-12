package beforeCourse.lists_5.moreEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P03_TakeOrSkipRope {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder(scanner.nextLine());
        List<Integer> numbers = new ArrayList<>();
        List<String> string = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) >= 48 && input.charAt(i) <= 57) {
                numbers.add(Integer.parseInt("" + input.charAt(i)));
            } else {
                string.add("" + input.charAt(i));
            }
        }
        List<Integer> takeList = new ArrayList<>();
        List<Integer> skipList = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (i % 2 == 0) {
                takeList.add(numbers.get(i));
            } else {
                skipList.add(numbers.get(i));
            }
        }
        String result = "";
        for (int i = 0; i < takeList.size(); i++) {
            int take = takeList.get(i);
            int skip = skipList.get(i);

            for (int j = 0; j < take; j++) {
                if (string.size() >= take) {
                    result = result + (string.get(j));
                } else {
                    take = string.size();
                    result = result + (string.get(j));
                }
            }

            for (int j = 0; j < take; j++) {
                string.remove(0);
            }

            for (int j = 0; j < skip; j++) {
                if (!string.isEmpty()) {
                    string.remove(0);
                }
            }
        }
        System.out.println(result);
    }
}
