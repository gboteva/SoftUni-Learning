package beforeCourse.AssociativeArrays_7.ex;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class P09_ForceBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        Map<String, List<String>> forceMap = new LinkedHashMap<>();

        while (!command.equals("Lumpawaroo")) {
            if (command.contains("|")) {
                String side = command.split(" \\| ")[0];
                String user = command.split(" \\| ")[1];

                //If there is no such force user and no such force side
                // -> create a new force side and add the force user to the corresponding side.
                if (forceMap.get(side) == null && !isThereUser(forceMap, user)) {
                    forceMap.put(side, new ArrayList<>());
                    forceMap.get(side).add(user);

                    //Only if there is no such force user in any force side
                    // -> add the force user to the corresponding side.
                } else if (forceMap.get(side) != null && !isThereUser(forceMap, user)) {
                    forceMap.get(side).add(user);

                    //If there is such force user already -> skip the command and continue to the next operation.
                } else if (isThereUser(forceMap, user)) {
                    command = scanner.nextLine();
                    continue;
                }

            } else {
                String user = command.split(" -> ")[0];
                String side = command.split(" -> ")[1];
                //•	If there is such force user already -> change their side.
                if (isThereUser(forceMap, user) && forceMap.get(side) != null) {
                    removeUser(forceMap, user);
                    forceMap.get(side).add(user);
                //•	If there is no such force user in any force side -> add the force user to the corresponding force side.
                } else if(!isThereUser(forceMap, user) && forceMap.get(side) != null) {
                    forceMap.get(side).add(user);
                } //•	If there is no such force user and no such force side
                // -> create new force side and add the force user to the corresponding side
                else if (forceMap.get(side) == null && !isThereUser(forceMap, user)) {
                    forceMap.put(side, new ArrayList<>());
                    forceMap.get(side).add(user);
                } else if (isThereUser(forceMap, user) && forceMap.get(side) == null) {
                    removeUser(forceMap, user);
                    forceMap.put(side, new ArrayList<>());
                    forceMap.get(side).add(user);
                }
                //•	Then you should print on the console: "{force_user} joins the {force_side} side!".

                System.out.printf("%s joins the %s side!%n", user, side);
            }

            command = scanner.nextLine();
        }
        for (Map.Entry<String, List<String>> entry : forceMap.entrySet()) {
            if (entry.getValue().size() != 0) {
                System.out.printf("Side: %s, Members: %d%n", entry.getKey(), entry.getValue().size());
                for (String user : entry.getValue()) {
                    System.out.println("! " + user);
                }
            }
        }

    }

    private static boolean isThereUser(Map<String, List<String>> forceMap, String user) {
        for (Map.Entry<String, List<String>> entry : forceMap.entrySet()) {
            if (entry.getValue().contains(user)) {
                return true;
            }
        }
        return false;
    }

    private static void removeUser(Map<String, List<String>> forceMap, String user) {
        for (Map.Entry<String, List<String>> entry : forceMap.entrySet()) {
            if (entry.getValue().contains(user)) {
                forceMap.get(entry.getKey()).remove(user);
            }
        }
    }

}
