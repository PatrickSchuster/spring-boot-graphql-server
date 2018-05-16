package com.example.DemoGraphQL;

import com.example.DemoGraphQL.filter.resolver.RootResolver;
import com.example.DemoGraphQL.filter.resolver.option.OptionsResolver;
import com.example.DemoGraphQL.resolver.BookResolver;
import com.example.DemoGraphQL.resolver.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
     */
    @Bean
    public BookResolver authorResolver() {
        return new BookResolver();
    }

    /**
     * The resolver for the Query.
     */
    @Bean
    public Query query(RootResolver rootResolver, OptionsResolver orderByResolver) {
        return new Query(rootResolver, orderByResolver);
    }

}
