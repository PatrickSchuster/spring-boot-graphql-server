package com.example.DemoGraphQL.filter.resolver;

import com.example.DemoGraphQL.filter.AbstractFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Neq extends AbstractFilter
{
    public Neq(String attribute, String value) {
        super(attribute, value);
    }

    @Override
    public Predicate getPredicate(CriteriaBuilder criteriaBuilder, From root) {
        return criteriaBuilder.notEqual(root.get(this.attribute), this.value);
    }
}
