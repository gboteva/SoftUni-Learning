package com.example.xml_2.repositories;

import com.example.xml_2.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c Where c.make =:maker order by c.model, c.travelledDistance desc ")
    List<Car> findAllByMakeOrderByModelAndTravelledDistance(@Param("maker") String maker);
}
