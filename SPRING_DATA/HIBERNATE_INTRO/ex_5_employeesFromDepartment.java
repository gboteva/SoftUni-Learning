import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ex_5_employeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        String departmentName = "Research and Development";

        entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.department.name = :departmentName" +
                        " ORDER BY e.salary ASC, e.id ASC", Employee.class)
                        .setParameter("departmentName", departmentName)
                                .getResultStream()
                                        .forEach(e->{
                                            String formatedString = String.format("%s %s from %s - $%.2f",
                                                    e.getFirstName(), e.getLastName(), departmentName, e.getSalary());
                                            System.out.println(formatedString);
                                        });


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
