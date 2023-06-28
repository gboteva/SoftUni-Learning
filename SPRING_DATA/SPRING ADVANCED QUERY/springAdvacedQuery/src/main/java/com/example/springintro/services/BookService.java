package com.example.springintro.services;

import com.example.springintro.entities.AgeRestriction;
import com.example.springintro.entities.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBook() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<Book> findAllAuthorWithBooksWithReleaseDateBefore(int year);


    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDateDescAndTitleAsc(String firstName, String lastName);

    List<String> findAllBooksTitleWithAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllBookTitlesWithGoldenEditionAndLessThan5000Copies();

    List<String> findAllBookTitlesAndPriceWithPriceLowerThan5AndGreaterThan40();

    List<String> findAllBookTitlesWithReleasedDateNot(int year);

    List<String> findAllBookWithReleaseDateBefore(String date);

    List<String> findAllBooksTitleContains(String text);

    List<String> findAllBookTitlesWhoseAuthorsLastNameStartWith(String text);

    int findAllBooksWithTitleLongerThan(int count);

    String findBookWithTitle(String title);

    int increaseBookCopies(LocalDate date, int copies);

    int removeBooksWithCopiesLowerThan(int numberOfCopies);
}
