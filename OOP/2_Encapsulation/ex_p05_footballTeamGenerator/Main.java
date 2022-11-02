package footballTeamGenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        Map<String, Team> teams = new LinkedHashMap<>();


        while (!"END".equals(command)) {
            String[] commandParts = command.split(";");
            String commandType = commandParts[0];
            String teamName = commandParts[1];
            try {
                switch (commandType) {
                    case "Team":
                        Team team = new Team(teamName);
                        teams.put(teamName, team);
                        break;

                    case "Add":
                        if (!teams.containsKey(teamName)) {
                            System.out.printf("Team %s does not exist.%n", teamName);
                        } else {
                            String playerName = commandParts[2];
                            int endurance = Integer.parseInt(commandParts[3]);
                            int sprint = Integer.parseInt(commandParts[4]);
                            int dribble = Integer.parseInt(commandParts[5]);
                            int passing = Integer.parseInt(commandParts[6]);
                            int shooting = Integer.parseInt(commandParts[7]);
                            Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                            teams.get(teamName).addPlayer(player);
                        }

                        break;

                    case "Remove":
                        String playerToRemove = commandParts[2];
                        teams.get(teamName).removePlayer(playerToRemove);
                        break;

                    case "Rating":
                        if (!teams.containsKey(teamName)) {
                            System.out.printf("Team %s does not exist.%n", teamName);
                        } else {
                            System.out.printf("%s - %.0f%n", teamName, teams.get(teamName).getRating());
                        }

                        break;

                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
            command = scanner.nextLine();
        }

    }
}
