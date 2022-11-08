package com.example.springadvancedqueringexercise.service.impl;

import com.example.springadvancedqueringexercise.model.entity.Author;
import com.example.springadvancedqueringexercise.model.entity.Book;
import com.example.springadvancedqueringexercise.repository.AuthorRepository;
import com.example.springadvancedqueringexercise.service.AuthorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String AUTHORS_FILE_PATH = "src/main/resources/files/authors.txt";

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (authorRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(AUTHORS_FILE_PATH))
                .forEach(row -> {
                    String[] fullName = row.split("\\s+");
                    Author author = new Author(fullName[0], fullName[1]);

                    authorRepository.save(author);
                });
    }

    @Override
    public Author getRandomAuthor() {
        long randomId = ThreadLocalRandom
                .current().nextLong(1,
                        authorRepository.count() + 1);

        return authorRepository
                .findById(randomId)
                .orElse(null);
    }

    @Override
    public List<String> getAllAuthorsOrderByCountOfTheirBooks() {
        return authorRepository
                .findAllByBooksSizeDESC()
                .stream()
                .map(author -> String.format("%s %s %d",
                        author.getFirstName(),
                        author.getLastName(),
                        author.getBooks().size()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Author> findAllByFirstNameEndingWith(String str) {
        return this.authorRepository.findAllByFirstNameEndingWith(str);
    }

    @Override
    public Map<String, Long> authorsWithTotalBookCopies() {
        List<Author> authors = this.authorRepository.findAll();

        Map<String, Long> authorsWithBookCopies = new LinkedHashMap<>();

        authors.forEach(author -> {
            long totalCopies = author.getBooks().stream().mapToLong(Book::getCopies).sum();
            authorsWithBookCopies.put(author.getFirstName() + " " + author.getLastName(), totalCopies);
        });
        LinkedHashMap<String, Long> sorted = new LinkedHashMap<>();

        authorsWithBookCopies.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));

        return sorted;
    }
}
