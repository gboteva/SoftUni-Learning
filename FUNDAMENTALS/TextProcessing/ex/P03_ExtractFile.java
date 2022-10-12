package beforeCourse.textProcessing_8.ex;

import java.util.Scanner;
import java.util.regex.Pattern;

public class P03_ExtractFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\\\");
        int lastIndex = input.length-1;
        String fileName = input[lastIndex].split("\\.")[0];
        String extension = input[lastIndex].split("\\.")[1];
        System.out.println("File name: "+ fileName);
        System.out.println("File extension: "+ extension);
    }
}
