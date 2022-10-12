package ex_p06_militaryElite;

public class SpecialisedSoldierImpl extends PrivateImpl implements  SpecialisedSoldier{
       private Corps corps;

    public SpecialisedSoldierImpl(int id, String firstName, String lastName, double salary, Corps corps) {
        super(id, firstName, lastName, salary);
        this.setCorps(corps);
    }

    @Override
    public Corps getCorps() {
        return this.corps;
    }

    private void setCorps (Corps corps) {
        if (corps.equals(Corps.Airforces) || corps.equals(Corps.Marines)) {
            this.corps = corps;
        }
    }
}
