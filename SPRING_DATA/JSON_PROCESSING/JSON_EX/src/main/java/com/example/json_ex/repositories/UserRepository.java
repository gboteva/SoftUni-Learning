package com.example.json_ex.repositories;

import com.example.json_ex.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN Product p ON p.seller.id = u.id" +
            " WHERE p.buyer.id IS NOT NULL" +
            " GROUP BY p.id" +
            " HAVING COUNT (p.id) > 0" +
            " ORDER BY u.lastName, u.firstName")

//    @Query("SELECT u FROM User u " +
//            "WHERE (SELECT COUNT(p) FROM Product p WHERE p.seller.id = u.id) > 0 " +
//            "ORDER BY u.lastName, u.firstName")
    List<User> findAllUsersWithMoreThanOneSoldProduct();


    @Query("SELECT u FROM User u WHERE size(u.soldProducts) > 0 ORDER BY size(u.soldProducts) DESC ")
    List<User> findAllByCountOfSoldProducts();
}
