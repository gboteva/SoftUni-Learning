package ex_p05_footballTeamGenerator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, Team> teams = new LinkedHashMap<>();

        while (!input.equals("END")){
            String[] tokens = input.split(";");
            String command = tokens[0];

            switch (command){
                case "Team":
                    String teamName = tokens[1];
                    try {
                        Team team = new Team(teamName);
                        teams.put(teamName.trim(),team);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;

                case "Add":
                    String toTeam = tokens[1];
                    String playerName = tokens[2];
                    int endurance = Integer.parseInt(tokens[3]);
                    int sprint = Integer.parseInt(tokens[4]);
                    int dribble = Integer.parseInt(tokens[5]);
                    int passing = Integer.parseInt(tokens[6]);
                    int shooting = Integer.parseInt(tokens[7]);

                    if (teams.containsKey(toTeam.trim())){
                        try {
                            Player player = new Player(playerName,endurance,sprint,dribble, passing, shooting);
                            teams.get(toTeam.trim()).addPlayer(player);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }

                    }else {
                        System.out.println("Team " + toTeam + " does not exist.");
                    }

                    break;

                case "Remove":
                    String fromTeam = tokens[1];
                    String playerToRemove = tokens[2];
                    if (teams.containsKey(fromTeam.trim())){
                        try {
                            teams.get(fromTeam.trim()).removePlayer(playerToRemove);
                        } catch (Exception e){
                            System.out.println("Player " + playerToRemove + " is not in " + fromTeam + " team.");
                        }
                    }

                    break;


                case "Rating":
                    String teamToShow = tokens[1];

                    if (teams.containsKey(teamToShow.trim())){

                        try {
                            System.out.printf("%s - %.0f%n", teamToShow.trim(), teams.get(teamToShow.trim()).getRating());
                        } catch (Exception e){
                            System.out.println(teamToShow.trim() + " - 0");
                        }


                    }else {
                        System.out.printf("Team %s does not exist.%n", teamToShow.trim());
                    }
                    break;

            }

            input = scanner.nextLine();
        }
    }
}
