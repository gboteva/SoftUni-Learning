package SetsAndMaps_03.ex;

import java.util.*;

public class P10_LogsAggregator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, TreeSet<String>> logs = new TreeMap<>();
        Map<String, Integer> logsDuration = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String[] data = scanner.nextLine().split("\\s+");
            String IP = data[0];
            String user = data[1];
            int duration = Integer.parseInt(data[2]);

            logs.putIfAbsent(user, new TreeSet<>());
            logs.get(user).add(IP);

            if (logsDuration.get(user)==null){
                logsDuration.putIfAbsent(user,duration);
            }else {
                logsDuration.put(user, logsDuration.get(user)+duration);
            }
        }

        for (Map.Entry<String, TreeSet<String>> entry : logs.entrySet()) {
            System.out.print(entry.getKey() + ": " + logsDuration.get(entry.getKey()) + " [");
            System.out.println((String.join(", ", entry.getValue()) + "]"));

        }


    }
}