package StacksAndQueues_01.ex;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class P03_MaximumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        ArrayDeque<Integer> sequence = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String[] command = scanner.nextLine().split("\\s+");

            switch (command[0]){
                case "1":
                    int elementToPush =Integer.parseInt(command[1]);
                    sequence.push(elementToPush);
                    break;
                case "2":
                    if (!sequence.isEmpty()){
                        sequence.pop();
                    }
                    break;
                case "3":
                    System.out.println(Collections.max(sequence));
                    break;
            }
        }
    }
}
