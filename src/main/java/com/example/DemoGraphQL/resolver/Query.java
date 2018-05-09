package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.DemoGraphQL.filter.Filter;
import com.example.DemoGraphQL.filter.AuthorFilter;
import com.example.DemoGraphQL.model.Author;
import com.example.DemoGraphQL.model.Book;
import com.example.DemoGraphQL.model.Model;
import com.example.DemoGraphQL.order.OrderBy;
import com.example.DemoGraphQL.repository.AuthorRepository;
import com.example.DemoGraphQL.repository.BookRepository;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * To define the operations of the root Query type.
 */
public class Query implements GraphQLQueryResolver {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DSLContext dslContext;

    public Query(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Author> findAllAuthors(AuthorFilter authorFilter) {
        String firstNameContains = authorFilter.getFirstNameContains();
        String lastNameContains = authorFilter.getLastNameContains();
        return authorRepository.findByFirstNameContainingIgnoreCase(firstNameContains);
    }

    public Book findBookWithId(Long id) {
        return bookRepository.findOne(id);
    }

    // Too simplistic, but kinda works...
    public Iterable<Author> findAuthors(List<Filter> filters) {
        if(filters == null){
            // TODO: how to do this in JOOQ?
            return authorRepository.findAll();
        }
        return find(filters, Author.class);
    }

    public Iterable<Book> findBooks(List<Filter> filters, OrderBy orderBy) {
        if(filters != null && orderBy != null){
            return find(filters, Book.class, orderBy);
        }
        if(filters == null && orderBy == null){
            // TODO: how to do this in JOOQ?
            // return dslContext.selectFrom(Book.class).fetch().into(Book.class); doesn't work...
            return bookRepository.findAll();
        }
        if(filters != null){
            return find(filters, Book.class);
        }
        if(orderBy != null){
            return find(Book.class, orderBy);
        }
        return null;
    }

    public <T extends Model> List<T> find(List<Filter> filters, Class<T> clazz){
        return null;
    }

    public <T extends Model> List<T> find(Class<T> clazz, OrderBy orderBy){
        return find(new ArrayList(), clazz, orderBy);
    }

    public <T extends Model> List<T> find(List<Filter> filters, Class<T> clazz, OrderBy orderBy){
        SelectJoinStep<Record> query = dslContext.select().from(clazz.getSimpleName());
        StringBuilder where = new StringBuilder();
        for (Filter filter: filters) {
            if(filter.getOperand() != null){
                where.append(filter.getOperand());
            }
            where.append("(");
            where.append(filter.getKey());
            where.append(filter.getOperator());
            where.append("'");
            where.append(filter.getValue());
            where.append("'");
            where.append(")");
        }
        List<T> result = query.where(where.toString()).fetch().into(clazz);
        return result;
    }

    /*
    private String concatFilters(Filter filter){
        if(filter.getOr() == null && filter.getAnd() == null){
            return concatSingleNode(filter);
        }
        if(filter.getOr() != null){
            //return getLowest(filter.getOr());
            return concatFilters(filter.getOr());
        }
        return getLowest(filter.getAnd());
    }
    */

    /***
    private String concatFilters(Filter filter){
        if(filter.getOr() == null && filter.getAnd() == null){
            return "";
        }
        if(filter.getOr() != null){
            return concatFilters(filter.getOr());
        }
    }

    private String concatList(List<Filter> filters, String operand){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < filters.size(); i++){
            Filter current = filters.get(i);
            stringBuilder.append(current.getKey());
            stringBuilder.append(current.getOperator());
            stringBuilder.append(current.getValue());
            if(i < filters.size() - 1){
                stringBuilder.append(operand);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
     ***/

    /***
    private String concatSingleNode(Filter filter){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        List concatList;
        if(filter.getAnd() != null){
            concatList = filter.getAnd();
        }
        if (){}
        for(int i = 0; i < concatList.size(); i++){
            Filter current = filter.getAnd().get(i);
            stringBuilder.append(current.getKey());
            stringBuilder.append(current.getOperator());
            stringBuilder.append(current.getValue());
            if(i < filter.getAnd().size() - 1){
                stringBuilder.append(filter.getOperand());
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
     ***/

    /***
    private Filter getLowestOrNode(Filter filter){
        if(filter.getOr() == null){
            return filter;
        }
        return getLowestOrNode(filter.getOr());
    }

    private Filter getRecursivelyDefinedFilter(Filter filter){
        if(filter.getOr() != null){
            return getRecursivelyDefinedFilter(filter.getOr());
        }
        if(filter.getAnd() != null){
            return getRecursivelyDefinedFilter(filter.getAnd());
        }
        // cannot go any lower in the tree
        return filter;
    }
    */

    public long countBooks() {
        return bookRepository.count();
    }

    public long countAuthors() {
        return authorRepository.count();
    }

}
