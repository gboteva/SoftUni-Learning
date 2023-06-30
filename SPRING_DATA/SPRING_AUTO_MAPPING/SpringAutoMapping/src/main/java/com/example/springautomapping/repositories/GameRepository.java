package com.example.springautomapping.repositories;

import com.example.springautomapping.models.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByTitle(String title);
}
