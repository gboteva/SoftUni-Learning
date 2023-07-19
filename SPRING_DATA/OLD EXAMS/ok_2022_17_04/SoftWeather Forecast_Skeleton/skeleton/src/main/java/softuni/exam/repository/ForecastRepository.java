package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.enums.DayOfWeek;

import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    boolean existsByDayOfWeekAndCity(DayOfWeek dayOfWeek, City city);

    @Query("SELECT f FROM Forecast f WHERE f.dayOfWeek = :dayOfWeek AND f.city.population < :population" +
            " order by f.maxTemperature desc, f.id")
    List<Forecast> findAllByDayOfWeekAndCityPopulationLessThan(
            @Param(value = "dayOfWeek") DayOfWeek dayOfWeek,
            @Param(value = "population") Integer population);


}
