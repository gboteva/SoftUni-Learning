package ex_p06_militaryElite;

public class PrivateImpl extends SoldierImpl implements Private{

    private double salary;

    public PrivateImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName);
        this.salary = salary;
    }

    @Override
    public double getSalary(){
        return this.salary;
    }

    @Override
    public String toString() {
        //Name: {firstName} {lastName} Id: {id} Salary: {salary}
        return String.format("Name: %s %s Id: %s Salary: %.2f", getFirstName(), getLastName(), getId(), getSalary());
    }


//    @Override
//    public int compareTo(PrivateImpl priv1, PrivateImpl priv2) {
//        int result = Integer.compare(priv2.getId(), priv1.getId());
//        return result;
//    }
}
