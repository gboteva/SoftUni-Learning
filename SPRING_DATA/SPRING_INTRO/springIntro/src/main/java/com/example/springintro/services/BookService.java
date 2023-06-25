package com.example.springintro.services;

import com.example.springintro.entities.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBook() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<Book> findAllAuthorWithBooksWithReleaseDateBefore(int year);


    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDateDescAndTitleAsc(String firstName, String lastName);
}
