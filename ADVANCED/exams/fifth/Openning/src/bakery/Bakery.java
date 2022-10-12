package bakery;

import java.util.ArrayList;
import java.util.List;

public class Bakery {
    private String name;
    private int capacity;
    private List<Employee> employees;

    public Bakery(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        employees = new ArrayList<>();
    }

    public void add(Employee employee) {
        if (this.employees.size()<capacity){
            employees.add(employee);
        }
    }

    public boolean remove(String name) {
        int indexToRemove = -1;
        for (int i = 0; i < this.employees.size(); i++) {
            if (employees.get(i).getName().equals(name)){
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove>-1){
            employees.remove(indexToRemove);
            return true;
        }else {
            return false;
        }
    }

    public Employee getOldestEmployee(){
        int maxAge = 0;
        Employee oldest = null;
        for (Employee e : this.employees ){
            if (e.getAge()>maxAge){
                maxAge = e.getAge();
                oldest = e;
            }
        }
        return oldest;
    }

    public Employee getEmployee(String name){
        for (Employee e : this.employees ){
            if (e.getName().equals(name)){
                return e;
            }
        }
        return null;
    }

    public int getCount(){
        return this.employees.size();
    }

    public String report(){
        StringBuilder builder = new StringBuilder();
        builder.append("Employees working at Bakery ").append(this.name).append(":").append("\n");
        for (Employee e : this.employees ){
            builder.append(e).append("\n");
        }
        return builder.toString();
    }

}
