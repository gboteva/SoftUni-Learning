package guild;

import java.util.ArrayList;
import java.util.List;

public class Guild {
    private String name;
    private int capacity;
    private List<Player> roster;

    public Guild(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void addPlayer(Player player){
        if (roster.size()<capacity){
            roster.add(player);
        }
    }

    public boolean removePlayer(String name){
        for (Player player : this.roster){
            if (player.getName().equals(name)){
                return this.roster.remove(player);
            }
        }
        return false;
    }

    public void promotePlayer(String name){
        for ( Player player : this.roster ){
            if (player.getName().equals(name)){
                if (!player.getRank().equals("Member")){
                    player.setRank("Member");
                }
                break;
            }
        }
    }

    public void demotePlayer(String name){
        for ( Player player : this.roster ){
            if (player.getName().equals(name)){
                if (!player.getRank().equals("Trial")){
                    player.setRank("Trial");
                }
                break;
            }
        }
    }

    public Player[] kickPlayersByClass(String clazz){
        List<Player> kickedList = new ArrayList<>();

        for (int i = 0; i < this.roster.size(); i++) {
            if (this.roster.get(i).getClazz().equals(clazz)){
                kickedList.add(this.roster.get(i));
            }
        }
        Player[] kicked = new Player[kickedList.size()];
        for (int i = 0; i < kickedList.size(); i++) {
            kicked[i] = kickedList.get(i);
        }

        this.roster.removeIf(player -> player.getClazz().equals(clazz));

        return kicked;
    }

    public int count(){
        return this.roster.size();
    }

    public String report(){
        StringBuilder builder = new StringBuilder();

        builder.append("Players in the guild: " + this.name + ":");
        for (Player player : this.roster ){
            builder.append("\n").append(player);
        }
        return String.valueOf(builder);
    }
}
