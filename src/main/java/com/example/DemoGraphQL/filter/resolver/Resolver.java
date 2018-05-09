package com.example.DemoGraphQL.filter.resolver;

import com.example.DemoGraphQL.filter.FilterFactory;
import com.example.DemoGraphQL.filter.FilterInput;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
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

    public void resolve(FilterInput filterInput, CriteriaBuilder criteriaBuilder, From root)
    {
        if(filterInput == null) {
            return;
        }

        if(filterInput.getEq() != null) {
            predicates.add(new Eq(filterInput.getEq().getAttribute(), filterInput.getEq().getValue()).getPredicate(criteriaBuilder, root));
        }
        else if(filterInput.getNeq() != null) {
            predicates.add(new Neq(filterInput.getNeq().getAttribute(), filterInput.getNeq().getValue()).getPredicate(criteriaBuilder, root));
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
