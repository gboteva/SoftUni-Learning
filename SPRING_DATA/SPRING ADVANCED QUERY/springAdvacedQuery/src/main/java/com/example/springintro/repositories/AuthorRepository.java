package com.example.springintro.repositories;

import com.example.springintro.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "SELECT a from Author a order by SIZE(a.books) DESC")

    List<Author> findAllByBooksCountDesc();

    List<Author> findAuthorsByFirstNameEndsWith(String searchString);

    @Query("select concat(a.firstName, ' ' ,a.lastName, ' - ', SUM(b.copies))  from Author a" +
            " join Book b ON a.id = b.author.id" +
            " ORDER BY SUM(b.copies) desc")
    List<String> findAuthorsNameAndCountOfBooksCopies();

    @Procedure("returnTotalAmountOfBooksByAuthorName")
    int returnTotalAmountOfBooksByAuthorName(@Param(value = "first_name")String first_name, @Param(value = "last_name") String last_name);
    
}
