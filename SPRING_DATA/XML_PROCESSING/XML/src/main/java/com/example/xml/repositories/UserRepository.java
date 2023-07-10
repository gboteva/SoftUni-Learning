package com.example.xml.repositories;

import com.example.xml.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        @Query("SELECT u FROM User u WHERE " +
            "(SELECT count(p) FROM Product p where p.seller.id = u.id AND p.buyer.id IS not null ) > 0" +
            " order by u.lastName, u.firstName")
    List<User> findAllWithAtLeastOneSoldProduct();

    @Query("SELECT u FROM User u WHERE " +
            "(SELECT count(p) FROM Product p where p.seller.id = u.id AND p.buyer.id IS not null ) > 0" +
            " order by (SELECT count(p) FROM Product p where p.seller.id = u.id AND p.buyer.id IS not null ) desc, u.lastName")
    List<User> findAllByCountOfSoldProducts();
}
