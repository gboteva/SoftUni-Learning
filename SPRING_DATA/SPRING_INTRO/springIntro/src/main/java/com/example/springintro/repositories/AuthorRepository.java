package com.example.springintro.repositories;

import com.example.springintro.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "SELECT a from Author a order by SIZE(a.books) DESC")

    List<Author> findAllByBooksCountDesc();
}
