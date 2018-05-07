package com.example.DemoGraphQL.filter.resolver;

import com.example.DemoGraphQL.filter.AbstractFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Eq extends AbstractFilter
{
    public Eq(String attribute, String value) {
        super(attribute, value);
    }

    @Override
    public Predicate getPredicate(CriteriaBuilder criteriaBuilder, Root root) {
        return criteriaBuilder.equal(root.get(this.attribute), this.value);
    }
}
