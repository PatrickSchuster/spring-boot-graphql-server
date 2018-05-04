package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.DemoGraphQL.filter.AllPurposeFilter;
import com.example.DemoGraphQL.filter.AuthorFilter;
import com.example.DemoGraphQL.model.Author;
import com.example.DemoGraphQL.model.Book;
import com.example.DemoGraphQL.operand.Operand;
import com.example.DemoGraphQL.repository.AuthorRepository;
import com.example.DemoGraphQL.repository.BookRepository;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private DSLContext create;

    public Query(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Author> findAllAuthors(AuthorFilter authorFilter) {
        String firstNameContains = authorFilter.getFirstNameContains();
        String lastNameContains = authorFilter.getLastNameContains();
        return authorRepository.findByFirstNameContainingIgnoreCase(firstNameContains);
    }

    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookWithId(Long id) {
        return bookRepository.findOne(id);
    }

    /*
    public List<Author> findAuthors(List<AllPurposeFilter> filters) throws SQLException {
        final SelectJoinStep<Record> temp = create.select().from(Author.class.getSimpleName());
        StringBuilder where = new StringBuilder();
        for (int i = 0; i < filters.size(); i++) {
            where.append(filters.get(i).getFirstProperty() + filters.get(i).getOperator() + "'" + filters.get(i).getSecondProperty() + "'");
            if (i < filters.size() - 1) {
                where.append(" AND ");
            }
        }
        //ResultSet resultSet = temp.fetchResultSet();
        ResultSet resultSet = temp.where(where.toString()).fetchResultSet();

        List<Author> authors = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            authors.add(new Author(id, firstName, lastName));
        }
        return authors;
    }
    */

    public List<Author> findAuthors(List<Operand> operands) {
        final SelectJoinStep<Record> temp = create.select().from(Author.class.getSimpleName());
        StringBuilder where = new StringBuilder();
        for (int i = 0; i < operands.size(); i++) {
            Operand operand = operands.get(i);
            where.append(" " + operand.getOperand() + " ");
            for(int j = 0; j < operand.getFilters().size(); j++) {
                AllPurposeFilter filter = operand.getFilters().get(j);
                where.append(filter.getFirstProperty() + filter.getOperator() + "'" + filter.getSecondProperty() + "'");
                if(j < operand.getFilters().size()-1){
                    where.append(" " + operand.getOperand()+ " ");
                }
            }
        }
        return null;
    }

    public long countBooks() {
        return bookRepository.count();
    }

    public long countAuthors() {
        return authorRepository.count();
    }

}
