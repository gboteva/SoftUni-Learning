package com.example.springintro;

import com.example.springintro.entities.AgeRestriction;
import com.example.springintro.entities.Book;
import com.example.springintro.services.AuthorService;
import com.example.springintro.services.BookService;
import com.example.springintro.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class CommandRunner implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;


    public CommandRunner(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }


    @Override
    public void run(String... args) throws Exception {
       // seedToDatabase();

       // printAllBooksTitleAfterReleaseDate(2000);

       // printAllAuthorsWithBookBeforeReleaseDate(1990);

      //  printNamesOfAllAuthorsAndNumberOfTheirBooks();

       // printBookInfoForBookByAuthor("George", "Powell");

        System.out.println("Enter task number: ");
        int taskNumber = Integer.parseInt(bufferedReader.readLine());

        switch (taskNumber){
            case 1: booksTitleByAgeRestriction();
            case 2: goldenBooks();
            case 3: booksByPrice();
            case 4: notReleasedBook();
            case 5: bookReleasedBeforeDate();
            case 6: authorsSearch();
            case 7: bookSearch();
            case 8: bookTitleSearch();
            case 9: countBooks();
            case 10: totalBookCopies();
            case 11: reducedBook();
            case 12: increaseBookCopies();
            case 13: removeBooks();
            case 14: storedProcedure();
        }
    }

    //Не работи -> грешка "Parameter index of 3 is out of range (1, 2)"
    //Ако я направя void работи?!?!?!?
    private void storedProcedure() throws IOException {
        System.out.println("Enter author's first name: ");
        String firstName = bufferedReader.readLine();

        System.out.println("Enter author's last name: ");
        String lastName = bufferedReader.readLine();

        System.out.println(authorService.getTotalAmountOfBooksOfAuthorWithNames(firstName, lastName));
    }

    private void removeBooks() throws IOException {
        System.out.println("Enter number of copies: ");
        int numberOfCopies = Integer.parseInt(bufferedReader.readLine());

        System.out.println(bookService.removeBooksWithCopiesLowerThan(numberOfCopies));
    }

    private void increaseBookCopies() throws IOException {
        System.out.println("Enter released date in format dd MMM yyyy: ");
        Locale.setDefault(Locale.US);
        LocalDate date = LocalDate.parse(bufferedReader.readLine(), DateTimeFormatter.ofPattern("dd MMM yyyy"));

        System.out.println("Enter increase number: ");
        int copies = Integer.parseInt(bufferedReader.readLine());

        System.out.println(bookService.increaseBookCopies(date, copies));

    }

    private void reducedBook() throws IOException {
        System.out.println("Enter book title: ");
        String title = bufferedReader.readLine();

        System.out.println(bookService.findBookWithTitle(title));
    }

    private void totalBookCopies() {
       authorService.findAllAuthorsWithTheirCopiesOfBooksOrderDescByTotalCopies()
               .forEach(System.out::println);
    }

    private void countBooks() throws IOException {
        System.out.println("Enter number of characters for book title: ");
        int count = Integer.parseInt(bufferedReader.readLine());

       int booksCount = bookService.findAllBooksWithTitleLongerThan(count);

        System.out.println(booksCount);
    }

    private void bookTitleSearch() throws IOException {
        System.out.println("Enter string: ");
        String text = bufferedReader.readLine();

        bookService.findAllBookTitlesWhoseAuthorsLastNameStartWith(text)
                .forEach(System.out::println);
    }

    private void bookSearch() throws IOException {
        System.out.println("Enter text:");
        String text = bufferedReader.readLine();
        bookService.findAllBooksTitleContains(text)
                .forEach(System.out::println);
    }

    private void authorsSearch() throws IOException {
        System.out.println("Enter string: ");
        String searchString = bufferedReader.readLine();

        authorService.findAllAuthorsNameWhoseFirstNameEndsWith(searchString)
                .forEach(System.out::println);
    }

    private void bookReleasedBeforeDate() throws IOException {
        System.out.println("Enter date in format dd-MM-yyyy: ");
        String date = bufferedReader.readLine();

        bookService.findAllBookWithReleaseDateBefore(date)
                .forEach(System.out::println);

    }

    private void notReleasedBook() throws IOException {
        System.out.println("Enter release year: ");
        int year = Integer.parseInt(bufferedReader.readLine());
        bookService.findAllBookTitlesWithReleasedDateNot(year)
                .forEach(System.out::println);
    }

    private void booksByPrice() {
        bookService.findAllBookTitlesAndPriceWithPriceLowerThan5AndGreaterThan40()
                .forEach(System.out::println);
    }

    private void goldenBooks() {
        bookService.findAllBookTitlesWithGoldenEditionAndLessThan5000Copies()
                .forEach(System.out::println);
    }

    private void booksTitleByAgeRestriction() throws IOException {
        System.out.println("Enter age Restriction value: ");
        AgeRestriction ageRestriction = AgeRestriction.valueOf(bufferedReader.readLine().toUpperCase());
        bookService.findAllBooksTitleWithAgeRestriction(ageRestriction)
                .forEach(System.out::println);
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
