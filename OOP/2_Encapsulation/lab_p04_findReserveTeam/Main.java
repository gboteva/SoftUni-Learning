package lab_p04_findReserveTeam;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Person> players = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String [] input = scanner.nextLine().split("\\s+");
            players.add(new Person(input[0], input[1], Integer.parseInt(input[2]), Double.parseDouble(input[3])));
        }

        Team team = new Team("Black Eagles");

        for (Person player: players) {
//           This code not working because the list is unmodifiable
//            if (player.getAge()<40){
//                team.getFirstTeam().add(player);
//            }else {
//                team.getReserveTeam().add(player);
//            }
           team.addPlayer(player);
        }

        System.out.println("First team have " + team.getFirstTeam().size() + " players");
        System.out.println("Reserve team have " + team.getReserveTeam().size() + " players");
    }
}
