package com.example.springintro;

import com.example.springintro.entities.Book;
import com.example.springintro.services.AuthorService;
import com.example.springintro.services.BookService;
import com.example.springintro.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandRunner implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;


    public CommandRunner(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }


    @Override
    public void run(String... args) throws Exception {
       // seedToDatabase();

       // printAllBooksTitleAfterReleaseDate(2000);

       // printAllAuthorsWithBookBeforeReleaseDate(1990);

      //  printNamesOfAllAuthorsAndNumberOfTheirBooks();

        printBookInfoForBookByAuthor("George", "Powell");
    }

    private void printBookInfoForBookByAuthor(String firstName, String lastName) {
        bookService.findAllBooksByAuthorFirstAndLastNameOrderByReleaseDateDescAndTitleAsc(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printNamesOfAllAuthorsAndNumberOfTheirBooks() {
        authorService.getAuthorsAndCountOfTheirBooks().forEach(System.out::println);
    }

    private void printAllAuthorsWithBookBeforeReleaseDate(int year) {
        bookService.findAllAuthorWithBooksWithReleaseDateBefore(year)
                .stream().map(Book::getAuthor)
                .distinct()
                .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));

    }

    private void printAllBooksTitleAfterReleaseDate(int year) {
        bookService.findAllBooksAfterYear(year)
                .stream().map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedToDatabase() throws IOException {
        categoryService.seedCategory();
        authorService.seedAuthor();
        bookService.seedBook();
    }


}
