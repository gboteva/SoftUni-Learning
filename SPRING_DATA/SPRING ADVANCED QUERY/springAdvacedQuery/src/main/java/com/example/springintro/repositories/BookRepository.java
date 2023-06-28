package com.example.springintro.repositories;

import com.example.springintro.entities.AgeRestriction;
import com.example.springintro.entities.Book;
import com.example.springintro.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findBooksByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByTypeAndCopiesLessThan(EditionType editionType, Integer copies);

    List<Book> findBooksByPriceLessThanOrPriceGreaterThan(BigDecimal lower, BigDecimal upper);

    List<Book> findBooksByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findBooksByTitleContains(String text);

    List<Book> findAllByAuthor_LastNameStartsWith(String text);

    @Query("select count (b) from Book b WHERE length(b.title) > :count")
    int findCountOfBooksWithTitleLongerThan(@Param(value = "count") int count);

    Book findBookByTitle(String title);

    @Modifying
    @Query("UPDATE Book b SET b.copies = b.copies + :copies WHERE b.releaseDate > :date")
    void increaseBookCopiesBy(@Param(value = "copies") int copies, @Param(value = "date") LocalDate date);

    List<Book> findBookByCopiesLessThan(int numberOfCopies);

    @Modifying
    void removeBookByCopiesLessThan(int numberOfCopies);
}
