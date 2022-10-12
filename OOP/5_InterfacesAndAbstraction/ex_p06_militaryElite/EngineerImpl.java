package ex_p06_militaryElite;

import java.util.ArrayList;
import java.util.List;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer{
    private List<Repair> repairs;

    public EngineerImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = new ArrayList<>();
    }

    public void addRepair(Repair repair){
        this.repairs.add(repair);
    }

    @Override
    public List<Repair> getRepairs() {
        return this.repairs;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).append("\n");
        builder.append("Corps: ").append(this.getCorps()).append("\n");
        builder.append("Repairs:");
        for (Repair repair : repairs) {
            builder.append("\n").append("  ").append(repair.toString());
        }

        return builder.toString();
    }
}
