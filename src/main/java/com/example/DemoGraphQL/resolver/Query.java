package com.example.DemoGraphQL.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.DemoGraphQL.filter.FilterInput;
import com.example.DemoGraphQL.filter.resolver.RootResolver;
import com.example.DemoGraphQL.filter.resolver.option.OptionsResolver;
import com.example.DemoGraphQL.model.Book;
import com.example.DemoGraphQL.options.Options;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectConditionStep;
import org.jooq.SelectSeekStepN;
import org.jooq.SortField;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.example.DemoGraphQL.tables.Author.AUTHOR;
import static com.example.DemoGraphQL.tables.Book.BOOK;


/**
 * To define the operations of the root Query type.
 */
public class Query implements GraphQLQueryResolver {

    @Autowired
    private DSLContext jooq;

    private RootResolver rootResolver;
    private OptionsResolver optionsResolver;

    public Query(RootResolver rootResolver, OptionsResolver optionsResolver) {
        this.rootResolver = rootResolver;
        this.optionsResolver = optionsResolver;
    }

    public Iterable<Book> findAllBooks() {
        return jooq
                .selectFrom(BOOK)
                .orderBy(BOOK.ID.asc())
                .fetch()
                .into(BOOK)
                .into(Book.class);
    }

    @SuppressWarnings("unchecked")
    public Iterable<Book> findBooks(FilterInput filters, Options options) {
        rootResolver.resolve(BOOK, filters);
        Condition condition = rootResolver.getCondition();

        SelectConditionStep<Record> selectWhere = jooq.select(BOOK.fields())
                .from(BOOK)
                .join(AUTHOR).onKey()
                .where(condition);

        if (options == null) {
            return selectWhere
                    .fetch()
                    .into(BOOK).into(Book.class);
        }

        final List<SortField> fields = optionsResolver.resolveOrderBy(options.getOrderBy());
        SelectSeekStepN<Record> selectWhereOrderBy = selectWhere.orderBy(fields.toArray(new SortField[fields.size()]));
        if (options.getLimit() != null) {
            return selectWhereOrderBy
                    .limit(optionsResolver.resolveLimit(options.getLimit()))
                    .fetch()
                    .into(BOOK).into(Book.class);
        }
        return selectWhereOrderBy.fetch().into(BOOK).into(Book.class);
    }
}
