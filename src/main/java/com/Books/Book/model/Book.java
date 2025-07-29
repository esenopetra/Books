package com.Books.Book.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "Books")
//@NoArgsConstructor
//@AllArgsConstructor
//@Setter
//@Getter
//@ToString
@Data
public class Book {
    @Id
    private long id;
    private String bookName;
    private String author;
    private String bookSummary;

    public Book() {}

    // All-args constructor
    public Book(long id, String bookName, String author, String bookSummary) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.bookSummary = bookSummary;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookSummary() {
        return bookSummary;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }

    // Optional: toString()
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", bookSummary='" + bookSummary + '\'' +
                '}';
    }
}

