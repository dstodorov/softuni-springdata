package com.example.springadvancedqueringexercise;

import com.example.springadvancedqueringexercise.model.entity.AgeRestriction;
import com.example.springadvancedqueringexercise.model.entity.EditionType;
import com.example.springadvancedqueringexercise.service.AuthorService;
import com.example.springadvancedqueringexercise.service.BookService;
import com.example.springadvancedqueringexercise.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        //p01_BooksTitlesByAgeRestriction();
        //p02_GoldenBooks();
        //p03_BooksByPrice();
        //p04_NotReleasedBooks();
        //p05_BookReleasedBeforeDate();
        //p06_AuthorsSearch();
        //p07_BooksSearch();
        //p08_BookTitleSearch();
        //p09_CountBooks();
        p10_TotalBooksCopies();

    }


    void p01_BooksTitlesByAgeRestriction() {
        String ageRestriction = new Scanner(System.in).nextLine();

        this.bookService.findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()))
                .forEach(book -> System.out.println(book.getTitle()));
    }

    void p02_GoldenBooks() {
        this.bookService.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    void p03_BooksByPrice() {
        this.bookService.findAllByPrice()
                .forEach(book -> System.out.printf("%s - $%.2f%n", book.getTitle(), book.getPrice()));
    }

    void p04_NotReleasedBooks() {
        int year = Integer.parseInt(new Scanner(System.in).nextLine());
        this.bookService.findAllNotReleasedBooks(year)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    void p05_BookReleasedBeforeDate() {
        String date = new Scanner(System.in).nextLine();
        LocalDate dateBefore = LocalDate.parse(date, DateTimeFormatter.ofPattern("d-MM-yyyy"));

        this.bookService.findAllByReleaseDateBefore(dateBefore)
                .forEach(book -> System.out.printf("%s %s %.2f%n", book.getTitle(), book.getEditionType().name(), book.getPrice()));
    }

    void p06_AuthorsSearch() {
        String str = new Scanner(System.in).nextLine();

        this.authorService.findAllByFirstNameEndingWith(str)
                .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }

    void p07_BooksSearch() {
        String str = new Scanner(System.in).nextLine();

        this.bookService.findAllByTitleContainsIgnoreCase(str)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    void p08_BookTitleSearch() {
        String str = new Scanner(System.in).nextLine();

        this.bookService.findAllByAuthorLastNameStartWith(str)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    void p09_CountBooks() {
        int symbols = Integer.parseInt(new Scanner(System.in).nextLine());
        System.out.println(this.bookService.countAllByTitleGreaterThan(symbols));
    }

    void p10_TotalBooksCopies() {

    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
