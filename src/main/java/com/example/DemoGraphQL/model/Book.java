package com.example.DemoGraphQL.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jooq.Record;
import org.jooq.TableField;

import static com.example.DemoGraphQL.tables.Book.BOOK;

@NoArgsConstructor
public class Book {

    @Getter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String isbn;

    @Getter
    @Setter
    private int pageCount;

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

    public static TableField<Record, String> getTitleJooqTableField() {
        return BOOK.TITLE;
    }

    public static TableField<Record, Long> getIdJooqTableField() {
        return BOOK.ID;
    }

    public static TableField<Record, String> getIsbnJooqTableField() {
        return BOOK.ISBN;
    }

    public static TableField<Record, Integer> getPageCountJooqTableField() {
        return BOOK.PAGE_COUNT;
    }

}
