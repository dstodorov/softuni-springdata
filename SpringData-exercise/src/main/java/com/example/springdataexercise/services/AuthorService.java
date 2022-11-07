package com.example.springdataexercise.services;

import com.example.springdataexercise.models.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    Author getRandomAuthor();

    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate);

    List<Author> findAll();

}
