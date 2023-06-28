package com.example.springintro.services;

import com.example.springintro.entities.*;
import com.example.springintro.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    private final static String BOOK_FILE_PATH = "src/main/resources/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBook() throws IOException {
        if (bookRepository.count() > 0){
            return;
        }

        Files.readAllLines(Path.of(BOOK_FILE_PATH)).forEach(row -> {
            String[] bookInfo = row.split("\\s+");
            Book book = createBookFromInfo(bookInfo);
            bookRepository.save(book);
        });

    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
       return bookRepository
               .findBooksByReleaseDateAfter(LocalDate.of(year-1, 12,31));
    }

    @Override
    public List<Book> findAllAuthorWithBooksWithReleaseDateBefore(int year) {
       return bookRepository.findBooksByReleaseDateBefore(LocalDate.of(year, 1, 1));
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDateDescAndTitleAsc(String firstName, String lastName) {
        return  bookRepository.findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(firstName, lastName)
                .stream().map(book -> String.format("%s, %s, %d",
                        book.getTitle(),
                        book.getReleaseDate().toString(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksTitleWithAgeRestriction(AgeRestriction ageRestriction) {

        return bookRepository.findAllByAgeRestriction(ageRestriction)
                .stream().map(Book::getTitle)
                .collect(Collectors.toList());

    }

    @Override
    public List<String> findAllBookTitlesWithGoldenEditionAndLessThan5000Copies() {
       return bookRepository.findAllByTypeAndCopiesLessThan(EditionType.GOLD, 5000)
                .stream().map(Book::getTitle)
                .collect(Collectors.toList());

    }

    @Override
    public List<String> findAllBookTitlesAndPriceWithPriceLowerThan5AndGreaterThan40() {
        BigDecimal lower = BigDecimal.valueOf(5);
        BigDecimal upper = BigDecimal.valueOf(40);

        return bookRepository.findBooksByPriceLessThanOrPriceGreaterThan(lower, upper)
                .stream()
                .map(book -> String.format("%s - %.2f", book.getTitle(), book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBookTitlesWithReleasedDateNot(int year) {
        LocalDate localDateLower = LocalDate.of(year, 1, 1);
        LocalDate localDateUpper = LocalDate.of(year, 12, 31);

       return bookRepository.findBooksByReleaseDateBeforeOrReleaseDateAfter(localDateLower, localDateUpper)
                .stream().map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBookWithReleaseDateBefore(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

       return bookRepository.findBooksByReleaseDateBefore(localDate)
                .stream().map(book -> String.format("%s %s %.2f",
                        book.getTitle(),
                        book.getType().name(),
                        book.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksTitleContains(String text) {

         return  bookRepository.findBooksByTitleContains(text.toLowerCase())
                .stream().map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBookTitlesWhoseAuthorsLastNameStartWith(String text) {
        return bookRepository.findAllByAuthor_LastNameStartsWith(text)
                .stream().map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public int findAllBooksWithTitleLongerThan(int count) {
       return bookRepository.findCountOfBooksWithTitleLongerThan(count);
    }

    @Override
    public String findBookWithTitle(String title) {
        Book book = bookRepository.findBookByTitle(title);

       return String.format("%s %s %s %.2f",
                book.getTitle(),
                book.getType().name(),
                book.getAgeRestriction().name(),
                book.getPrice());

    }

    @Transactional
    @Override
    public int increaseBookCopies(LocalDate date, int copies) {

        bookRepository.increaseBookCopiesBy(copies, date);

        List<Book> affectedBooks = bookRepository.findBooksByReleaseDateAfter(date);

        return affectedBooks.size() * copies;
    }
    @Transactional
    @Override
    public int removeBooksWithCopiesLowerThan(int numberOfCopies) {
        int affectedBooks = bookRepository.findBookByCopiesLessThan(numberOfCopies).size();

        bookRepository.removeBookByCopiesLessThan(numberOfCopies);

        return affectedBooks;
    }


    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate.parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        int copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo).skip(5).collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();

        Set<Category> categories = categoryService.getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
