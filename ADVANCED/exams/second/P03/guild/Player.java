package guild;

public class Player {
    private String name;
    private String clazz;
    private String rank;
    private String description;

    public Player(String name, String clazz) {
        this.name = name;
        this.clazz = clazz;
        this.rank = "Trial";
        this.description = "n/a";
    }

    public String getName(){
        return this.name;
    }

    public String getRank() {
        return rank;
    }

    public String getClazz() {
        return clazz;
    }

    public void setRank (String rank){
        this.rank = rank;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Player " + this.name + ": " + this.clazz);
        builder.append("\n").append("Rank: " + this.rank);
        builder.append("\n").append("Description: " + this.description);

        return builder.toString();
   }
}
