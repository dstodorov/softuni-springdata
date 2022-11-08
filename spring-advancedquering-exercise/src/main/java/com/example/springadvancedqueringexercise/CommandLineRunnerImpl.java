package com.example.springadvancedqueringexercise;

import com.example.springadvancedqueringexercise.model.entity.AgeRestriction;
import com.example.springadvancedqueringexercise.model.entity.Book;
import com.example.springadvancedqueringexercise.service.AuthorService;
import com.example.springadvancedqueringexercise.service.BookService;
import com.example.springadvancedqueringexercise.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
        p01_BooksTitlesByAgeRestriction();
    }

    void p01_BooksTitlesByAgeRestriction() {
        String ageRestriction = new Scanner(System.in).nextLine();

        this.bookService.findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()))
                .forEach(book -> System.out.println(book.getTitle()));
    }
    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
