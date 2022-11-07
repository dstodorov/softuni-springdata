package com.example.springdataexercise.services;

import com.example.springdataexercise.models.*;
import com.example.springdataexercise.repositories.AuthorRepository;
import com.example.springdataexercise.repositories.BookRepository;
import com.example.springdataexercise.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;

    private static final String RESOURCE_PATH = "src/main/resources/files/";
    private static final String AUTHORS_FILE_PATH = RESOURCE_PATH + "authors.txt";
    private static final String BOOKS_FILE_PATH = RESOURCE_PATH + "books.txt";
    private static final String CATEGORIES_FILE_PATH = RESOURCE_PATH + "categories.txt";

    @Override
    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(s -> s.split("\\s+"))
                .map(names -> new Author(names[0], names[1]))
                .forEach(author -> authorRepository.save(author));

    }

    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(BOOKS_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(this::getBookObject)
                .forEach(book -> bookRepository.save(book));

    }

    private Book getBookObject(String str) {

        String[] bookInfo = str.split("\\s+");

        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate.parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        int copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo).skip(5).collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService.getRandomCategories();

        return new Book(ageRestriction, copies, editionType, price, releaseDate, title, author, categories);

    }

    @Override
    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(Category::new)
                .forEach(category -> categoryRepository.save(category));
    }
}
