package com.example.DemoGraphQL;

import com.example.DemoGraphQL.filter.FilterFactory;
import com.example.DemoGraphQL.filter.resolver.Resolver;
import com.example.DemoGraphQL.model.Author;
import com.example.DemoGraphQL.model.Book;
import com.example.DemoGraphQL.repository.AuthorRepository;
import com.example.DemoGraphQL.repository.BookRepository;
import com.example.DemoGraphQL.resolver.BookResolver;
import com.example.DemoGraphQL.resolver.Query;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DemoGraphQlApplication {

    /**
     * The graphQL server can be reached on host:8080/graphql (via POST) or on host:8080/graphiql in the browser.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoGraphQlApplication.class, args);
    }

    /**
     * The resolver for the Book.
     *
     * @param authorRepository
     * @return
     */
    @Bean
    public BookResolver authorResolver(AuthorRepository authorRepository) {
        return new BookResolver(authorRepository);
    }

    /**
     * The resolver for the Query.
     *
     * @param authorRepository
     * @param bookRepository
     * @return
     */
    @Bean
    public Query query(AuthorRepository authorRepository, BookRepository bookRepository, Resolver resolver) {
        return new Query(authorRepository, bookRepository, resolver);
    }

    /**
     * Add some DB entries before the application finishes building.
     *
     * @param authorRepository
     * @param bookRepository
     * @return
     */
    @Bean
    public CommandLineRunner demo(AuthorRepository authorRepository, BookRepository bookRepository) {
        return (args) -> {
            Author king = new Author("Stephen", "King");
            Author christie = new Author("Agatha", "Christie");
            Author brown = new Author("Dan", "Brown");
            Author smith = new Author("Zadie", "Smith");
            authorRepository.save(Arrays.asList(king, christie, brown, smith));

            bookRepository.save(new Book("Misery", "123456", 700, king));
            bookRepository.save(new Book("Needful Things", "345352", 900, king));
            bookRepository.save(new Book("The stand", "1122534", 1800, king));
            bookRepository.save(new Book("Sleeping beauties", "113546", 1000, king));
            bookRepository.save(new Book("The shining", "554478", 680, king));
            bookRepository.save(new Book("Illuminati", "345352", 900, brown));
            bookRepository.save(new Book("And then there where none", "375643", 600, christie));
            bookRepository.save(new Book("Home going", "643642", 500, smith));

        };
    }
}
