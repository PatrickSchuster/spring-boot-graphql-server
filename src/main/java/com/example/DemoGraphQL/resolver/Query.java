package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.DemoGraphQL.filter.FilterInput;
import com.example.DemoGraphQL.filter.resolver.RootResolver;
import com.example.DemoGraphQL.model.Author;
import com.example.DemoGraphQL.model.Book;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.DemoGraphQL.tables.Author.AUTHOR;
import static com.example.DemoGraphQL.tables.Book.BOOK;


/**
 * To define the operations of the root Query type.
 */
public class Query implements GraphQLQueryResolver {

    @Autowired
    private DSLContext jooq;

    private RootResolver rootResolver;

    public enum Operator {
        AND, OR
    }

    public Query(RootResolver rootResolver) {
        this.rootResolver = rootResolver;
    }

    public Iterable<Book> findAllBooks() {
        return jooq.selectFrom(BOOK)
                .orderBy(BOOK.ID.asc())
                .fetch()
                .into(Book.class);
    }

    public Iterable<Book> findBooks(FilterInput filters){
        rootResolver.resolve(BOOK, filters);
        return jooq.selectFrom(BOOK)
                .where(rootResolver.getCondition())
                .fetch()
                .into(Book.class);
    }

    public Iterable<Book> findBooks(FilterInput filters, Operator operator, FilterInput author){
        rootResolver.resolve(BOOK, filters);
        Condition a = rootResolver.getCondition();

        rootResolver.resolve(AUTHOR, author);
        Condition b = rootResolver.getCondition();

        Condition finalCondition;
        switch (operator) {
            case OR:
                finalCondition = DSL.or(a, b);
                break;
            default:
            case AND:
                finalCondition = DSL.and(a, b);
                break;
        }

        return jooq.select(BOOK.fields())
                .from(BOOK)
                .join(AUTHOR).onKey()
                .where(finalCondition)
                .fetch()
                .into(BOOK).into(Book.class);
    }

    public long countBooks() {
        return jooq.selectCount().from(BOOK).fetchOne(0, int.class);
    }

    public long countAuthors() {
        return jooq.selectCount().from(AUTHOR).fetchOne(0, int.class);
    }

}
