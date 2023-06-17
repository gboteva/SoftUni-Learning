import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class ex_13_removeTowns {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String townNameToRemove = scanner.nextLine();

        Integer townId = entityManager.createQuery("SELECT t.id FROM Town t WHERE t.name = :townName", Integer.class)
                .setParameter("townName", townNameToRemove)
                .getSingleResult();

        setNullOfEmployeeAddresses(entityManager, townId);

        List addressesToDelete = entityManager.createQuery("SELECT a FROM Address a WHERE a.town.name = :townName")
                .setParameter("townName", townNameToRemove).getResultList();

        for (Object address : addressesToDelete) {
            entityManager.remove(address);
        }

        entityManager.createQuery("DELETE FROM Town t WHERE t.id = :townId")
                        .setParameter("townId", townId);

        System.out.printf("%d address in %s deleted%n", addressesToDelete.size(), townNameToRemove);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void setNullOfEmployeeAddresses(EntityManager entityManager, Integer townId) {
        entityManager.createQuery("SELECT e FROM Employee e WHERE e.address.town.id = :townId", Employee.class)
                .setParameter("townId", townId).
                getResultStream().forEach(employee -> {
                    employee.setAddress(null);
                });
    }
}
