package ex_p06_militaryElite;

public class SpyImpl extends SoldierImpl implements Spy {
    private String codeNumber;

    public SpyImpl(int id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
    }

    @Override
    public String getCodeNumber() {
        return this.codeNumber;
    }

    @Override
    public String toString() {
        //Name: {firstName} {lastName} Id: {id}
        //Code Number: {codeNumber}
        return String.format("Name: %s %s Id: %s%nCode Number: %s",
                getFirstName(), getLastName(), getId(), getCodeNumber());
    }
}
