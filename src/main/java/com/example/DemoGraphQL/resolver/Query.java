package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.DemoGraphQL.filter.FilterInput;
import com.example.DemoGraphQL.filter.resolver.RootResolver;
import com.example.DemoGraphQL.model.Book;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.DemoGraphQL.tables.Author.AUTHOR;
import static com.example.DemoGraphQL.tables.Book.BOOK;

/**
 * To define the operations of the root Query type.
 */
public class Query implements GraphQLQueryResolver
{
    @Autowired
    private DSLContext jooq;

    private RootResolver rootResolver;

    public Query(RootResolver rootResolver) {
        this.rootResolver = rootResolver;
    }

    public Iterable<Book> findAllBooks() {
        return jooq
                .selectFrom(BOOK)
                .orderBy(BOOK.ID.asc())
                .fetch()
                .into(BOOK)
                .into(Book.class);
    }

    public Iterable<Book> findBooks(FilterInput filters)
    {
        rootResolver.resolve(BOOK, filters);
        Condition condition = rootResolver.getCondition();

        return jooq
                .select(BOOK.fields())
                .from(BOOK)
                .join(AUTHOR).onKey()
                .where(condition)
                .fetch()
                .into(Book.class);
    }
}
