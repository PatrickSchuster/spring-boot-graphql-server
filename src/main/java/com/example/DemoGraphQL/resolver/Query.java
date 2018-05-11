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

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DSLContext dslContext;

    private FilterFactory filterFactory;
    private Resolver resolver;

    public enum Operator {
        AND, OR
    }

    public Query() {

    }

    public Iterable<Book> findAllBooks() {
        return dslContext.selectFrom(BOOK).where(BOOK.AUTHOR_ID.eq(new Long(1))).orderBy(BOOK.ID.asc()).fetch().into(Book.class);
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
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);

        resolver.resolve(filters, criteriaBuilder, root);
        List<Predicate> p = resolver.getPredicates();
        resolver.clear();

        query.where(p.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

    public Iterable<Book> findBooks(FilterInput filters, Operator operator, FilterInput author)
    {
        return dsl.selectFrom(BOOK).fetch().into(Book.class);


//        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
//        Root<Book> root = query.from(Book.class);
//
//        resolver.resolve(filters, criteriaBuilder, root);
//        List<Predicate> p = resolver.getPredicates();
//        resolver.clear();
//
//        Join<Book, Author> authorJoin = root.join("author", JoinType.LEFT);
//
//        resolver.resolve(author, criteriaBuilder, authorJoin);
//
//        if(operator == null
//            || operator == Operator.AND)
//        {
//            p.addAll(resolver.getPredicates());
//            query.where(p.toArray(new Predicate[0]));
//        }
//        else {
//            Predicate w = criteriaBuilder.or(criteriaBuilder.and(p.toArray(new Predicate[0])), criteriaBuilder.and(resolver.getPredicates().toArray(new Predicate[0])));
//            query.where(w);
//        }
//
//        resolver.clear();
//
//
//
//        return entityManager.createQuery(query).getResultList();
    }
}
