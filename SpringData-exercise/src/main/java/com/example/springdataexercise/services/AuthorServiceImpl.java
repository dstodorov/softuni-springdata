package com.example.springdataexercise.services;

import com.example.springdataexercise.models.Author;
import com.example.springdataexercise.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService{
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long count = this.authorRepository.count();

        long id = new Random().nextLong(count) + 1;

        if (authorRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Author is missing");
        }

        return authorRepository.findById(id).get();
    }

    @Override
    public List<Author> findDistinctByBooksReleaseDateBefore(LocalDate releaseDate) {
        return this.authorRepository.findDistinctByBooksReleaseDateBefore(releaseDate);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

}
