package com.example.json_ex_2.repositories;

import com.example.json_ex_2.models.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("select s from Supplier s where s.isImporter = false ")
    List<Supplier> findAllByImporterFalse();
}
