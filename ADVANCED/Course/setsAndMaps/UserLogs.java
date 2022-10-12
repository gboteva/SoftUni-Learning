package setsAndMaps;

import java.util.*;

public class UserLogs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Map<String, Map<String, Integer>> logs = new TreeMap<>();

        while (!input.equals("end")) {
            String[] tokens = input.split("\\s+");
            String IP = tokens[0].split("=")[1];
            String user = tokens[2].split("=")[1];
            String message = tokens[1].split("=")[1];

            if (!logs.containsKey(user)) {
                logs.put(user, new LinkedHashMap<>());
            }

            if (!logs.get(user).containsKey(IP)) {
                logs.get(user).put(IP, 1);
            } else {
                int currentCountMessages = logs.get(user).get(IP);
                logs.get(user).put(IP, currentCountMessages + 1);
            }

            input = scanner.nextLine();
        }

        for (Map.Entry<String, Map<String, Integer>> entry : logs.entrySet()) {
            String user = entry.getKey();
            System.out.println(user + ": ");
            Map<String, Integer> ips = entry.getValue();
            int count = 0;
            for (Map.Entry<String, Integer> ip : ips.entrySet()) {
                if (count < ips.size() - 1) {
                    System.out.print(ip.getKey() + " => " + ip.getValue() + ", ");
                } else {
                    System.out.print(ip.getKey() + " => " + ip.getValue() + ".");
                }
                count++;
            }
            System.out.println();

        }
    }
}
