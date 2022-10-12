package beforeCourse.lists_5.ex;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P07_AppendArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> input = Arrays.stream(scanner.nextLine().split("\\|")).collect(Collectors.toList());
        Collections.reverse(input);

        String result = "";
        for (String element : input) {
            result += element + " ";
        }
        result=result.replaceAll("\\s+", " ");
        result = result.trim();
        System.out.println(result);

    }
}
