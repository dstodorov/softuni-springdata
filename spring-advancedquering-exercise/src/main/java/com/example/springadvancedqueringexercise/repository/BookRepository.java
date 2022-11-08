package com.example.springadvancedqueringexercise.repository;

import com.example.springadvancedqueringexercise.model.entity.AgeRestriction;
import com.example.springadvancedqueringexercise.model.entity.Book;
import com.example.springadvancedqueringexercise.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    @Query("SELECT b FROM Book b WHERE b.price < 5 OR b.price > 40")
    List<Book> findAllByPrice();

    @Query("SELECT b FROM Book b WHERE YEAR(b.releaseDate) <> :year")
    List<Book> findAllNotReleasedBooks(@Param("year") int year);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByTitleContainsIgnoreCase(String str);

    @Query("SELECT b FROM Book b JOIN b.author a WHERE a.lastName like :str%")
    List<Book> findAllByAuthorLastNameStartWith(String str);

    @Query("SELECT COUNT(b) FROM Book b WHERE LENGTH(b.title) > :symbols")
    int countAllByTitleGreaterThan(int symbols);
}
