package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.DemoGraphQL.model.Book;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.DemoGraphQL.tables.Book.BOOK;

/**
 * To define the operations of the root Query type.
 */
public class Query implements GraphQLQueryResolver {

    @Autowired
    private DSLContext dslContext;

    public Query() {

    }

    public Iterable<Book> findAllBooks() {
        return dslContext.selectFrom(BOOK).where(BOOK.AUTHOR_ID.eq(new Long(1))).orderBy(BOOK.ID.asc()).fetch().into(Book.class);
    }

    /*
    public Book findBookWithId(Long id){
        return bookRepository.findOne(id);
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public long countBooks() {
        return bookRepository.count();
    }

    public long countAuthors() {
        return authorRepository.count();
    }
    */

}
