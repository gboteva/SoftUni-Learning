import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ex_2_changeCasing {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery("SELECT t FROM Town t " +
                "WHERE char_length(t.name) <=5", Town.class)
                .getResultStream()
                .forEach(t-> {
                    t.setName(t.getName().toUpperCase());
                    entityManager.persist(t);
                });


        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
