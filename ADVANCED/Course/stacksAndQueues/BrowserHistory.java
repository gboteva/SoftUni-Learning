package stacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BrowserHistory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        ArrayDeque<String> history = new ArrayDeque<>();

        String currentPage = null;

        while (!input.equals("Home")) {

            if (!input.equals("back")){  // URL on the input

                //if until now there is another URL -> add input to the history;
                if (currentPage != null){
                    history.push(currentPage);
                }
                //current page is current input
                currentPage = input;

            }else { // back to the input
                // if history is empty - print output, read new input and continue;
                if (history.isEmpty()){
                    System.out.println("no previous URLs");
                    input = scanner.nextLine();
                    continue;

                 //if the browser has history -> current page is last page from the stack
                }else {
                    currentPage = history.pop();
                }
            }
            System.out.println(currentPage);

            input = scanner.nextLine();
        }


    }
}
