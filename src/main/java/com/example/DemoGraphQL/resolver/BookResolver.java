package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.DemoGraphQL.model.Author;
import com.example.DemoGraphQL.model.Book;

/**
 * Resolver for the complex fields of the Book entity. In this case: only the "author" field of a Book.
 */
public class BookResolver implements GraphQLResolver<Book> {

    public BookResolver() {

    }

    public Author getAuthor(Book book) {
        //return authorRepository.findOne(book.getAuthor().getId());
        return null;
    }

}
