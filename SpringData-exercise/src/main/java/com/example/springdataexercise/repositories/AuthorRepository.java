package com.example.springdataexercise.repositories;

import com.example.springdataexercise.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate localDate);
}
