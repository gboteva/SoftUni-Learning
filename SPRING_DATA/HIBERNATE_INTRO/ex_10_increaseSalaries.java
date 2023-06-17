import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ex_10_increaseSalaries {
    public static void main(String[] args) {
         EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
         EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();


        entityManager.createQuery("UPDATE Employee e SET e.salary = e.salary*1.12 WHERE e.department.id IN (1, 2, 4, 11)")
                        .executeUpdate();

        entityManager.createQuery("SELECT e FROM Employee e WHERE e.department.id IN (1, 2, 4, 11)", Employee.class)
                .getResultStream()
                        .forEach(employee -> {
                            System.out.printf("%s %s ($%.2f)%n", employee.getFirstName(), employee.getLastName(), employee.getSalary());
                        });



        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
