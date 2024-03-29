package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Mechanic;

@Repository
public interface MechanicsRepository extends JpaRepository<Mechanic, Long> {


    boolean existsByEmailOrFirstName(String email, String firstName);

    boolean existsByFirstName(String firstName);

    Mechanic findByFirstName(String firstName);
}
