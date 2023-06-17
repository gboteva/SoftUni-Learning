import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ex_4_employeesWithSalaryOver50000 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery("SELECT e.firstName FROM Employee e WHERE e.salary > 50000", String.class)
                        .getResultStream()
                        .forEach(System.out::println);

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
