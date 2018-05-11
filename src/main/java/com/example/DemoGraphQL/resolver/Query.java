package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.DemoGraphQL.model.Author;
import com.example.DemoGraphQL.model.Book;
import com.example.DemoGraphQL.order.OrderBy;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.TableField;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.DemoGraphQL.tables.Author.AUTHOR;
import static com.example.DemoGraphQL.tables.Book.BOOK;

/**
 * To define the operations of the root Query type.
 */
public class Query implements GraphQLQueryResolver {

    @Autowired
    private DSLContext jooq;

    public Query() {

    }

    public Iterable<Book> findAllBooks() {
        //return jooq.selectFrom(BOOK).where(BOOK.AUTHOR_ID.eq(new Long(1))).orderBy(BOOK.ID.asc()).fetch().into(Book.class);
        return jooq.selectFrom(BOOK).fetch().into(Book.class);
    }

    public Iterable<Author> findAllAuthors() {
        return jooq.selectFrom(AUTHOR).fetch().into(Author.class);
    }

    public long countBooks() {
        return jooq.selectCount().from(BOOK).fetchOne(0, int.class);
    }

    public long countAuthors() {
        return jooq.selectCount().from(AUTHOR).fetchOne(0, int.class);
    }

    public Iterable<Book> findBooks(OrderBy orderBy) {
        TableField tableField = null;
        String field = orderBy.getField();
        if(field.equalsIgnoreCase("title")){
            tableField = Book.getTitleJooqTableField();
        } else if(field.equalsIgnoreCase("id")){
            tableField = Book.getIdJooqTableField();
        } else if(field.equalsIgnoreCase("isbn")){
            tableField = Book.getIsbnJooqTableField();
        } else if(field.equalsIgnoreCase("pageCount")){
            tableField = Book.getPageCountJooqTableField();
        }
        if(orderBy.getDirection().equalsIgnoreCase("ASC")){
            tableField.asc();
        } else{
            tableField.desc();
        }
        return jooq.select().from(BOOK).orderBy(tableField).fetch().into(Book.class);
    }

}
