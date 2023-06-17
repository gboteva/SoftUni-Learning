
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;


public class ex_9_findLatest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Project> projects = entityManager.createQuery("SELECT p FROM Project p" +
                        " ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList();


        ZoneId zone = ZoneId.of("UTC");

        String dateTimePattern = "yyyy-MM-dd HH:mm:ss.S";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern).withZone(zone);



        System.out.println();

        projects.stream()
               .sorted(Comparator.comparing(Project::getName)).forEach( project -> {
                   String name = project.getName();
                   String description = project.getDescription();
                   String start = project.getStartDate().format(formatter);
                    String end = project.getEndDate() == null? "null" : project.getEndDate().format(formatter.withZone(zone));

                   System.out.printf("Project name: %s%n", name);
                   System.out.printf("\tProject Description: %s%n", description);
                   System.out.printf("\tProject Start Date:%s%n", start);
                   System.out.printf("\tProject End Date: %s%n", end);
               });



        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
