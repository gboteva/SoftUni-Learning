package com.example.json_ex_2.repositories;

import com.example.json_ex_2.models.entities.Car;
import com.example.json_ex_2.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
   // @Query("select c FROM Car c where c.make = :maker ORDER BY c.model, c.travelledDistance DESC ")
    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(@Param(value = "maker") String make);

}
