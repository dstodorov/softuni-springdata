package com.example.springdataexercise.services;

import com.example.springdataexercise.models.Book;
import com.example.springdataexercise.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findByReleaseDateAfter(LocalDate date) {
        return this.bookRepository.findByReleaseDateAfter(date);
    }

    @Override
    public List<Book> findAllByFirstNameAndLastNameOrderByReleaseDateDescAndTitleAsc(String firstName, String lastName) {
        return this.bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName);
    }

}
