package ex_p06_militaryElite;

import java.util.ArrayList;
import java.util.List;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {
    private List<Mission> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary, corps);
        this.missions = new ArrayList<>();
    }

    public void addMission(Mission mission){
        this.missions.add(mission);
    }

    @Override
    public List<Mission> getMissions() {
        return this.missions;
    }

    @Override
    public String toString() {
        //"Name: {firstName} {lastName} Id: {id} Salary: {salary}
        //Corps: {corps}
        //Missions:
        //  {mission1 ToString()}
        //  {mission2 ToString()}
        //  …
        //  {missionN ToString()}"
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).append("\n");
        builder.append("Corps: ").append(this.getCorps()).append("\n");
        builder.append("Missions:");
        for (Mission mission : this.missions) {
            builder.append("\n").append("  ").append(mission.toString());
        }

        return builder.toString();
    }
}
