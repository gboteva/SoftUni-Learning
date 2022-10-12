package ex_p06_militaryElite;

public class RepairImpl implements Repair{
    private String partName;
    private int hoursWorked;

    public RepairImpl(String partName, int hoursWorked) {
        this.partName = partName;
        this.hoursWorked = hoursWorked;
    }


    @Override
    public String getPartName() {
        return this.partName;
    }

    @Override
    public int getHoursWorked() {
        return this.hoursWorked;
    }

    @Override
    public String toString() {
        //"Part Name: {partName} Hours Worked: {hoursWorked}"
        return String.format("Part Name: %s Hours Worked: %d", this.partName, this.hoursWorked);
    }
}
