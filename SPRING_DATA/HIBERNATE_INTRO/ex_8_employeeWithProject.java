import entities.Address;
import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;
import java.util.stream.Collectors;

public class ex_8_employeeWithProject {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());


        Employee employee = entityManager.createQuery("SELECT e FROM Employee e" +
                        " WHERE e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();


        List<Project> projects = employee.getProjects().stream()
                .sorted(Comparator.comparing(Project::getName))
                .collect(Collectors.toList());


        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        projects.forEach(p->{
            System.out.printf("\t %s%n", p.getName());
        });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
