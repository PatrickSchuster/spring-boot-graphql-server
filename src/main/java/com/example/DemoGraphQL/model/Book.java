package com.example.DemoGraphQL.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
public class Book {

    @Id
    @Column(name = "book_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Column(name = "book_title", nullable = false)
    @Getter
    @Setter
    private String title;

    @Column(name = "book_isbn", nullable = false)
    @Getter
    @Setter
    private String isbn;

    @Column(name = "book_pageCount", nullable = false)
    @Getter
    @Setter
    private int pageCount;

    @ManyToOne
    @JoinColumn(name = "author_id",
            nullable = false, updatable = false)
    @Getter
    @Setter
    private Author author;

    public Book(String title, String isbn, int pageCount, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
