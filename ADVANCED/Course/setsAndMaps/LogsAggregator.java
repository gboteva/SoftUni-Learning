package setsAndMaps;

import java.util.*;

public class LogsAggregator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        Map<String,Integer> userDuration = new TreeMap<>();
        Map<String, Set<String>> userIp = new TreeMap<>();

        while (n -- > 0){
            String [] tokens = scanner.nextLine().split("\\s+");
            String IP = tokens[0];
            String user = tokens[1];
            int duration = Integer.parseInt(tokens[2]);

            if (userDuration.containsKey(user)){
                int currentDuration = userDuration.get(user);
                userDuration.put(user, currentDuration+duration);
            }else {
                userDuration.put(user, duration);
            }

            if (userIp.containsKey(user)){
                userIp.get(user).add(IP);
            }else {
                userIp.put(user, new TreeSet<>());
                userIp.get(user).add(IP);
            }
        }

        for (Map.Entry<String, Integer> entry : userDuration.entrySet()) {
            String userName = entry.getKey();
            int duration = entry.getValue();
            String ipList = String.join(", ", userIp.get(userName));
            System.out.printf("%s: %d [%s]%n", userName, duration, ipList);
        }
        
    }
}
