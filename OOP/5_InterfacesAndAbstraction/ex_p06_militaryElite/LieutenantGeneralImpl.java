package ex_p06_militaryElite;

import java.util.LinkedHashMap;
import java.util.Map;


public class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral {
    private Map<Integer,Private> privates;

    public LieutenantGeneralImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.privates = new LinkedHashMap<>();
    }

    @Override
    public Map<Integer, Private> getPrivate() {
        return this.privates;
    }

    @Override
    public void addPrivate(PrivateImpl priv) {
        if (!this.privates.containsKey(priv.getId())){
            this.privates.put(priv.getId(), priv);
        }
    }

    @Override
    public String toString() {
        //"Name: {firstName} {lastName} Id: {id} Salary: {salary}
        //Privates:
        //  {private1 ToString()}
        //  {private2 ToString()}
        //  …
        //  {privateN ToString()}"
        //Note: privates must be sorted by id in descending order.

        StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).append("\n");
        builder.append("Privates:");

        this.privates.entrySet().stream()
                .sorted((a,b)-> b.getKey().compareTo(a.getKey()))
                .forEach(e->builder.append("\n").append("  ").append(e.getValue().toString()));

        return builder.toString();
    }


}
