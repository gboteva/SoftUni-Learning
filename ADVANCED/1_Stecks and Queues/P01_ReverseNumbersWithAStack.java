package StacksAndQueues_01.ex;

import java.util.ArrayDeque;
import java.util.Scanner;

public class P01_ReverseNumbersWithAStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String [] input = scanner.nextLine().split("\\s+");
        ArrayDeque <String> reversed = new ArrayDeque<>();
        for (int i = 0; i < input.length; i++) {
            reversed.push(input[i]);
        }
        while (!reversed.isEmpty()){
            System.out.print(reversed.pop() + " ");
        }
    }
}
