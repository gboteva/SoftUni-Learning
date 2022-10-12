package stacksAndQueues;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BrowserHistoryExtend {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String URL = scanner.nextLine();

        ArrayDeque<String> browser = new ArrayDeque<>();
        ArrayDeque<String> forward = new ArrayDeque<>();

        while (!URL.equals("Home")){
            if (URL.equals("back")) {

                if (browser.size() < 2) {
                    System.out.println("no previous URLs");
                } else {
                    String previousURL = browser.pop();
                    forward.addFirst(previousURL);
                    System.out.println(browser.peek());
                }
            }else if (URL.equals("forward")){
                if (forward.isEmpty()){
                    System.out.println("no next URLs");
                }else {
                    String forwardURL = forward.poll();
                    browser.push(forwardURL);
                    System.out.println(forwardURL);
                }

            }else {
                browser.push(URL);
                System.out.println(browser.peek());
                forward.clear();
            }

            URL= scanner.nextLine();
        }

    }
}
