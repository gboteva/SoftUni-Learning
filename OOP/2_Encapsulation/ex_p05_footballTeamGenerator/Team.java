package ex_p05_footballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.setName(name);
        players = new ArrayList<>();
    }

    private void setName(String name) {
        if (!name.trim().isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("A name should not be empty.");
        }
    }

    public String getName() {
        return this.name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String playerName) {
        int indexToRemove = -1;
        for (Player player : this.players) {
            if (player.getName().equals(playerName.trim())) {
                indexToRemove = this.players.indexOf(player);
                break;
            }
        }
        if (indexToRemove!=-1) {
            this.players.remove(indexToRemove);
        }else {
            throw new IllegalArgumentException();
        }
    }

    public double getRating(){
//        double sum = 0;
//       for (Player player : players ){
//         sum+= player.overallSkillLevel();
//       }
//       return sum/this.players.size();

     return this.players.stream().mapToDouble(p->p.overallSkillLevel()).average().getAsDouble();
    }
}
