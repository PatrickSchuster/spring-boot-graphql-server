package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.DemoGraphQL.model.Author;
import com.example.DemoGraphQL.model.Book;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.DemoGraphQL.tables.Author.AUTHOR;
import static com.example.DemoGraphQL.tables.Book.BOOK;

/**
 * Resolver for the complex fields of the Book entity. In this case: only the "author" field of a Book.
 */
public class BookResolver implements GraphQLResolver<Book> {

    @Autowired
    private DSLContext jooq;

    public BookResolver() {

    }

    public Author getAuthor(Book book) {
        return jooq.select()
                .from(AUTHOR)
                .join(BOOK)
                .on(AUTHOR.ID.eq(BOOK.AUTHOR_ID))
                .where(BOOK.ID.eq(book.getId()))
                .fetchOneInto(AUTHOR)
                .into(Author.class);
    }
}
