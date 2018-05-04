package com.example.DemoGraphQL.filter.resolver;

import com.example.DemoGraphQL.filter.FilterInput;
import com.example.DemoGraphQL.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Resolver
{
    @PersistenceContext
    private EntityManager entityManager;

    private List<FilterInput> filters;

    public void resolve(String rootClass)
    {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);


    }
}
