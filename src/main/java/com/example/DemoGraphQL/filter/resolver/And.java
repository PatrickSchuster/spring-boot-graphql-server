package com.example.DemoGraphQL.filter.resolver;

import com.example.DemoGraphQL.filter.FilterFactory;
import com.example.DemoGraphQL.filter.FilterInput;
import com.example.DemoGraphQL.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

@Component
public class And
{
    @PersistenceContext
    private EntityManager entityManager;

    private List<Predicate> predicates;



    public And(EntityManager entityManager) {
        this.entityManager = entityManager;
        predicates = new ArrayList<>();
    }

    public Predicate resolve(FilterInput filterInput)
    {
        return null;
//        FilterFactory filterFactory = new FilterFactory();
//        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
//        Root<Book> root = query.from(Book.class);
//
//        if(filterInput.getValue() != null) {
//            try {
//                predicates.add(filterFactory.getInstance(filterInput).getPredicate(criteriaBuilder, root));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        if(filterInput.getAnd() != null) {
//            for (FilterInput f : filterInput.getAnd()) {
//                resolve(f);
//            }
//        }
//
//        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
