package StacksAndQueues_01.ex;

import java.util.ArrayDeque;
import java.util.Scanner;

public class P07_SimpleTextEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        StringBuilder text = new StringBuilder();
        ArrayDeque<String> textState = new ArrayDeque<>();


        for (int i = 0; i <n ; i++) {
            String [] command = scanner.nextLine().split("\\s+");
            switch (command[0]){
                case "1":
                    textState.push(text.toString());
                    String textToAdd = command[1];
                    text.append(textToAdd);
                    break;
                case "2":
                    textState.push(text.toString());
                    int countToDelete = Integer.parseInt(command[1]);
                    if (countToDelete<=text.length()){
                        int start = text.length()-countToDelete;
                        text.delete(start,start+countToDelete);

                    }else {
                        text = new StringBuilder();
                        textState.push(text.toString());
                    }

                    break;
                case "3":
                    int position = Integer.parseInt(command[1]);
                    if ((position-1) <=text.length() && (position-1)>=0){
                        System.out.println(text.charAt(position-1));
                    }
                    break;
                case "4":

                    text= new StringBuilder(textState.pop());
                    break;
            }
        }

    }
}
