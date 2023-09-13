package com.example.book_borrowing.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Book")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "introduction")
    private String introduction;

    public Book() {
    }

    public Book(String isbn, String name, String author, String introduction) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.introduction = introduction;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
