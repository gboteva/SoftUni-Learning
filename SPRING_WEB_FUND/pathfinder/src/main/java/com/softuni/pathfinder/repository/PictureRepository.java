package com.softuni.pathfinder.repository;

import com.softuni.pathfinder.model.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {
    @Query("SELECT p FROM PictureEntity p WHERE p.route.id =:id")
    List<PictureEntity> findAllByRouteId(Long id);
}
