import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ex_7_addressWithEmployeeCount {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Address> addresses = entityManager
                .createQuery("SELECT a FROM Address a ORDER BY a.employees.size DESC", Address.class)
                        .setMaxResults(10)
                                .getResultList();

        for (Address address : addresses) {
            String addressText = address.getText();
            String townName =  address.getTown() == null ? "":  address.getTown().getName();
            long employeeCount = address.getEmployees().size();
            System.out.printf("%s, %s - %d employees%n", addressText, townName,employeeCount);
        };


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
