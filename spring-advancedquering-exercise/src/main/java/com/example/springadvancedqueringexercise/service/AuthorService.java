package com.example.springadvancedqueringexercise.service;

import com.example.springadvancedqueringexercise.model.entity.Author;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<Author> findAllByFirstNameEndingWith(String str);

    Map<String, Long> authorsWithTotalBookCopies();
}
