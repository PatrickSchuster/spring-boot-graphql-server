package com.example.DemoGraphQL.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface FilterInterface {
    Predicate getPredicate(CriteriaBuilder criteriaBuilder, From root);


}
