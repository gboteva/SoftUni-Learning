import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ex_6_addingAndUpdatedAddress {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        String addressText = "Vitoshka 15";

        Address address = new Address();
        address.setText(addressText);
        entityManager.persist(address);

        String employeeLastName = "Margheim";

        entityManager.createQuery("UPDATE  Employee e" +
                                         " SET e.address = :address" +
                                         " WHERE e.lastName = :employeeLastName")
                                            .setParameter("address", address)
                                            .setParameter("employeeLastName", employeeLastName)
                                            .executeUpdate();


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
