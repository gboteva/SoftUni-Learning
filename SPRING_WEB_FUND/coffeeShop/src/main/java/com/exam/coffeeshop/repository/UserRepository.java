package com.exam.coffeeshop.repository;

import com.exam.coffeeshop.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsernameAndPassword(String username, String password);

    UserEntity findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM UserEntity u ORDER BY size(u.orders) desc ")
    List<UserEntity> findAllUsersAndCountOfTheirOrders();
}
