package footballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        setName(name);
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void removePlayer (String playerName){
        Player player = players.stream().filter(p -> p.getName().equals(playerName))
                .findFirst().orElse(null);
        if (player == null){
            throw new IllegalArgumentException("Player " + playerName + " is not in " + name + " team.");
        }else {
            this.players.remove(player);
        }
    }

    public double getRating(){
        return this.players.stream().mapToDouble(Player::overallSkillLevel).average().orElse(0);
    }
}
