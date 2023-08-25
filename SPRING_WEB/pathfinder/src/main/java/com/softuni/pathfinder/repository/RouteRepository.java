package com.softuni.pathfinder.repository;

import com.softuni.pathfinder.model.entity.CategoryEntity;
import com.softuni.pathfinder.model.entity.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
    @Query("SELECT r FROM RouteEntity r WHERE :category MEMBER OF r.categories")
    List<RouteEntity> findAllByCategoryName(@Param("category")CategoryEntity category);
}
