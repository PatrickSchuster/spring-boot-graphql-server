package com.example.DemoGraphQL.filter.resolver;

import com.example.DemoGraphQL.filter.FilterFactory;
import com.example.DemoGraphQL.filter.FilterInput;
import com.example.DemoGraphQL.filter.FilterInterface;
import com.example.DemoGraphQL.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class And
{
    @PersistenceContext
    private EntityManager entityManager;

    private FilterFactory filterFactory;

    private List<FilterInput> filters;

    public And(FilterFactory filterFactory)
    {
        filters = Arrays.asList(
            new FilterInput("title","=","Misery", null, null),
            new FilterInput("id","=","Misery", null, null)
        );

        this.filterFactory = filterFactory;
    }

    public void resolve()
    {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);

        List<Predicate> predicates = new ArrayList<>();

        for (FilterInput filter : filters) {

            try {
                predicates.add(this.filterFactory.getInstance(filter).getPredicate(criteriaBuilder, root));
            }
            catch (Exception e) {

            }
        }

        query.where(predicates.toArray(new Predicate[0]));
    }
}
