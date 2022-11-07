package com.example.springdataexercise;

import com.example.springdataexercise.services.AuthorService;
import com.example.springdataexercise.services.BookService;
import com.example.springdataexercise.services.CategoryService;
import com.example.springdataexercise.services.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
@Component
public class ConsoleRunner implements CommandLineRunner {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final SeedService seedService;

    public ConsoleRunner(BookService bookService, AuthorService authorService, CategoryService categoryService, SeedService seedService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws IOException {
        //seedService.seedAll();
        //p01_printBooksAfter2000Year();
        //p02_printAuthorsWithAtLease1BookBefore1990();
        //p03_printAuthorsSortedByBooksCount();
        //p04_printAllBookFromAuthorOrderedByReleaseNameAndBookTitle();

    }

    void p01_printBooksAfter2000Year() {
        LocalDate yearAfter = LocalDate.of(2000, 12, 31);
        this.bookService.findByReleaseDateAfter(yearAfter).forEach(book -> System.out.println(book.getTitle()));
    }

    void p02_printAuthorsWithAtLease1BookBefore1990() {
        LocalDate yearBefore = LocalDate.of(1990, 1, 1);
        this.authorService.findDistinctByBooksReleaseDateBefore(yearBefore)
                .forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }

    void p03_printAuthorsSortedByBooksCount() {
        this.authorService.findAll()
                .stream()
                .sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName() + " " + a.getBooks().size()));
    }

    void p04_printAllBookFromAuthorOrderedByReleaseNameAndBookTitle() {
        this.bookService.findAllByFirstNameAndLastNameOrderByReleaseDateDescAndTitleAsc("George", "Powell")
                .forEach(book -> System.out.printf("%s %s %d%n", book.getTitle(), book.getReleaseDate().toString(), book.getCopies()));
    }
}
