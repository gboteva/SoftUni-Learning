import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class ex_3_containsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();
        Scanner scanner = new Scanner(System.in);

        String[] name = scanner.nextLine().split("\\s+");

        Long employeeCount = entityManager.createQuery("SELECT count(e.firstName) FROM Employee e " +
                        "WHERE e.firstName = :first_name AND e.lastName = :last_name", Long.class)
                .setParameter("first_name", name[0])
                .setParameter("last_name", name[1])
                .getSingleResult();

        if (employeeCount == 0){
                System.out.println("No");
            }else {
                System.out.println("Yes");
            }

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
