package com.example.DemoGraphQL.filter.resolver;

import com.example.DemoGraphQL.filter.FilterFactory;
import com.example.DemoGraphQL.filter.FilterInput;
import com.example.DemoGraphQL.model.Book;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class Resolver
{
    @PersistenceContext
    private EntityManager entityManager;

    private FilterFactory filterFactory;

    private List<Predicate> predicates;

    private Resolver(FilterFactory filterFactory)
    {
        this.filterFactory = filterFactory;
        this.predicates = new ArrayList<>();
    }

    public void resolve(FilterInput filterInput, CriteriaBuilder criteriaBuilder, Root root)
    {
        if(filterInput.getAttribute() != null
                && filterInput.getOperator() != null
                && filterInput.getValue() != null)
        {
            try {
                predicates.add(filterFactory.getInstance(filterInput).getPredicate(criteriaBuilder, root));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(filterInput.getOr() != null) {
            for(FilterInput or: filterInput.getOr()) {
                resolve(or, criteriaBuilder, root);
            }
        }

        if(filterInput.getAnd() != null) {
            for(FilterInput and: filterInput.getAnd()) {
                resolve(and, criteriaBuilder, root);
            }
        }
    }

    public List<Predicate> getPredicates() {
        return new ArrayList<>(predicates);
    }

    public void clear() {
        predicates.clear();
    }
}
