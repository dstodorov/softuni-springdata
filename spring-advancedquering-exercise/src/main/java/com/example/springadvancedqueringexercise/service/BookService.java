package com.example.springadvancedqueringexercise.service;

import com.example.springadvancedqueringexercise.model.entity.AgeRestriction;
import com.example.springadvancedqueringexercise.model.entity.Book;
import com.example.springadvancedqueringexercise.model.entity.EditionType;
import com.example.springadvancedqueringexercise.model.entity.dto.BookInformation;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);


    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);
    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPrice();
    List<Book> findAllNotReleasedBooks(int year);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByTitleContainsIgnoreCase(String str);

    List<Book> findAllByAuthorLastNameStartWith(String str);

    int countAllByTitleGreaterThan(int symbols);

    BookInformation findFirstByTitle(String title);

}
