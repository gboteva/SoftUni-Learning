import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class ex_12_employeeMaxSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        final List<String> departmentNames = entityManager.createQuery("SELECT e.department.name " +
                        "FROM Employee e " +
                        "GROUP BY e.department.id " +
                        "HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000", String.class)
                .getResultList();

        final List<BigDecimal> maxSalaries = entityManager.createQuery("SELECT MAX(e.salary) " +
                        "FROM Employee e " +
                        "GROUP BY e.department.id " +
                        "HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000", BigDecimal.class)
                .getResultList();

        for (int i = 0; i < departmentNames.size(); i++) {
            System.out.println(departmentNames.get(i) + " " + maxSalaries.get(i));
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
