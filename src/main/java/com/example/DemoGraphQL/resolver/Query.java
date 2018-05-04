package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.DemoGraphQL.filter.FilterFactory;
import com.example.DemoGraphQL.filter.FilterInput;
import com.example.DemoGraphQL.model.Author;
import com.example.DemoGraphQL.model.Book;
import com.example.DemoGraphQL.repository.AuthorRepository;
import com.example.DemoGraphQL.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * To define the operations of the root Query type.
 */
public class Query implements GraphQLQueryResolver {

    @PersistenceContext
    private EntityManager entityManager;

    private FilterFactory filterFactory;

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Query(AuthorRepository authorRepository, BookRepository bookRepository, FilterFactory filterFactory) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.filterFactory = filterFactory;
    }

    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

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

    public Iterable<Book> findBooks(FilterInput filters)
    {

        return bookRepository.findAll();
//        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
//        Root<Book> root = query.from(Book.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        for (FilterInput filter : filters) {
//
//            try {
//                predicates.add(this.filterFactory.getInstance(filter).getPredicate(criteriaBuilder, root));
//            }
//            catch (Exception e) {
//
//            }
//        }
//
//        query.where(predicates.toArray(new Predicate[0]));
//
//        return entityManager.createQuery(query).getResultList();
    }
}
