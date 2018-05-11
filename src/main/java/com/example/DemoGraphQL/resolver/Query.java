package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.DemoGraphQL.filter.FilterInput;
import com.example.DemoGraphQL.filter.resolver.Resolver;
import com.example.DemoGraphQL.model.Book;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.example.DemoGraphQL.tables.Author.AUTHOR;
import static com.example.DemoGraphQL.tables.Book.BOOK;


/**
 * To define the operations of the root Query type.
 */
public class Query implements GraphQLQueryResolver {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DSLContext dslContext;

    private Resolver resolver;

    public enum Operator {
        AND, OR
    }

    public Query(Resolver resolver) {
        this.resolver = resolver;
    }

    public Iterable<Book> findAllBooks() {
        return dslContext
                .selectFrom(BOOK)
                .orderBy(BOOK.ID.asc())
                .fetch()
                .into(Book.class);
    }

    public Iterable<Book> findBooks(FilterInput filters)
    {
        resolver.resolve(BOOK, filters);

        return dslContext
                .selectFrom(BOOK)
                .where(resolver.getConditions())
                .fetch()
                .into(Book.class);
    }

    public Iterable<Book> findBooks(FilterInput filters, Operator operator, FilterInput author)
    {
        resolver.resolve(BOOK, filters);
        List<Condition> b = resolver.getConditions();

        resolver.resolve(AUTHOR, author);
        b.addAll(resolver.getConditions());

        return dslContext
                .select(BOOK.fields())
                .from(BOOK)
                .join(AUTHOR).onKey()
                .where(b)
                .fetch()
                .into(Book.class);
    }
}
