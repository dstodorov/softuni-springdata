package com.example.springadvancedqueringexercise;

import com.example.springadvancedqueringexercise.model.entity.AgeRestriction;
import com.example.springadvancedqueringexercise.model.entity.Book;
import com.example.springadvancedqueringexercise.model.entity.EditionType;
import com.example.springadvancedqueringexercise.service.AuthorService;
import com.example.springadvancedqueringexercise.service.BookService;
import com.example.springadvancedqueringexercise.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
        p04_BookReleasedBeforeDate();
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

    void p04_BookReleasedBeforeDate() {
        String date = new Scanner(System.in).nextLine();
        LocalDate dateBefore = LocalDate.parse(date, DateTimeFormatter.ofPattern("d-MM-yyyy"));

        this.bookService.findAllByReleaseDateBefore(dateBefore)
                .forEach(book -> System.out.printf("%s %s %.2f%n", book.getTitle(), book.getEditionType().name(), book.getPrice()));
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
