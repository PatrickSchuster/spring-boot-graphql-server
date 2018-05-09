package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.DemoGraphQL.model.Author;
import com.example.DemoGraphQL.model.Book;
import com.example.DemoGraphQL.repository.AuthorRepository;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Resolver for the complex fields of the Book entity. In this case: only the "author" field of a Book.
 */
public class BookResolver implements GraphQLResolver<Book> {

    private AuthorRepository authorRepository;

    @Autowired
    private DSLContext dslContext;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Book book) {
        return authorRepository.findOne(book.getAuthor().getId());
        /*
        Author result = dslContext
                .select()
                .from(Author.class.getSimpleName())
                .where("ID = " + book.getAuthor().getId())
                .fetchOne()
                .into(Author.class);
        return result;
        */
    }

}
