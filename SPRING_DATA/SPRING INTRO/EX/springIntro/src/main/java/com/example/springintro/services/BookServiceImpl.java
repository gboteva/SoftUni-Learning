package com.example.springintro.services;

import com.example.springintro.entities.*;
import com.example.springintro.repositories.BookRepository;
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
