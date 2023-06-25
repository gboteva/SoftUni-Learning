package com.example.springintro.repositories;

import com.example.springintro.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findBooksByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);
}
