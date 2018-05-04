package com.example.DemoGraphQL.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Eq extends AbstractFilter
{
    Eq(String attribute, String value) {
        super(attribute, value);
    }

    @Override
    public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root root) {
        return criteriaBuilder.equal(root.get(this.attribute), this.value);
    }
}
