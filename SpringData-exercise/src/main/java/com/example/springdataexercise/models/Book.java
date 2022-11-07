package com.example.springdataexercise.models;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "age_regisration")
    @Enumerated
    private AgeRestriction ageRegistration;

    private int copies;

    private String description;

    @Column(name = "edition_type")
    @Enumerated
    private EditionType editionType;

    private BigDecimal price;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToMany
    private Set<Category> categories;

    public Book() {
    }

    public Book(AgeRestriction ageRegistration, int copies, EditionType editionType, BigDecimal price, LocalDate releaseDate, String title, Author author, Set<Category> categories) {
        this.ageRegistration = ageRegistration;
        this.copies = copies;
        this.editionType = editionType;
        this.price = price;
        this.releaseDate = releaseDate;
        this.title = title;
        this.author = author;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AgeRestriction getAgeRegistration() {
        return ageRegistration;
    }

    public void setAgeRegistration(AgeRestriction ageRegistration) {
        this.ageRegistration = ageRegistration;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
