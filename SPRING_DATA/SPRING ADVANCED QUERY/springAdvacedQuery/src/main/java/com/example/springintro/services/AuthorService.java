package com.example.springintro.services;

import com.example.springintro.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthor() throws IOException;

    Author getRandomAuthor();

    List<String> getAuthorsAndCountOfTheirBooks();

    List<String> findAllAuthorsNameWhoseFirstNameEndsWith(String searchString);

    List<String> findAllAuthorsWithTheirCopiesOfBooksOrderDescByTotalCopies();

    int getTotalAmountOfBooksOfAuthorWithNames(String firstName, String lastName);
}
