package SetsAndMaps_03.ex;

import java.util.*;

public class P08_UserLogs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, LinkedHashMap<String, Integer>> userLogs = new TreeMap<>();

        while (!input.equals("end")) {
            String[] inputArr = input.split("\\s+");
            String IP = inputArr[0].split("=")[1];
            String user = inputArr[2].split("=")[1];

            if (!userLogs.containsKey(user)) {
                userLogs.put(user, new LinkedHashMap<>());
                userLogs.get(user).put(IP, 1);
            } else {
                if (!userLogs.get(user).containsKey(IP)) {
                    userLogs.get(user).put(IP, 1);
                } else {
                    int currentCountLogs = userLogs.get(user).get(IP);
                    userLogs.get(user).put(IP, currentCountLogs + 1);
                }
            }

            input = scanner.nextLine();
        }


        userLogs.entrySet().stream().sorted((f,s)-> f.getKey().compareTo(s.getKey()))
                .forEach(e->{
                    System.out.println(e.getKey() + ":");
                    List<String> ip = new ArrayList<>();
                    Map<String, Integer> map = e.getValue();
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        String toAdd = entry.getKey() + " => " + entry.getValue();
                        ip.add(toAdd);
                    }
                    System.out.println(String.join(", ", ip) + ".");

                });
    }
}
