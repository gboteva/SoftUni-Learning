package footballTeamGenerator;

public class Player {
    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        setName(name);
        setEndurance(endurance);
        setSprint(sprint);
        setDribble(dribble);
        setPassing(passing);
        setShooting(shooting);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    private void setEndurance(int endurance) {
        checkStats(endurance,"Endurance");
        this.endurance = endurance;
    }

    private void setSprint(int sprint) {
        checkStats(sprint,"Sprint");
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        checkStats(dribble,"Dribble");
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        checkStats(passing,"Passing");
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        checkStats(shooting,"Shooting");
        this.shooting = shooting;
    }

    public String getName() {
        return name;
    }

    public double overallSkillLevel(){
        return (endurance + sprint + dribble + passing + shooting) / 5.0;
    }

    private void checkStats(int value, String statName){
        if (value < 0 || value > 100){
            throw new IllegalArgumentException(statName + " should be between 0 and 100.");
        }
    }
}
