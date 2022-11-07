package com.example.springdataexercise.services;

import com.example.springdataexercise.models.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    List<Book> findByReleaseDateAfter(LocalDate date);

    List<Book> findAllByFirstNameAndLastNameOrderByReleaseDateDescAndTitleAsc(String firstName, String lastName);

}
