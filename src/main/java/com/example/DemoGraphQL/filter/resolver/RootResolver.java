package com.example.DemoGraphQL.filter.resolver;

import com.example.DemoGraphQL.filter.AbstractFilter;
import com.example.DemoGraphQL.filter.FilterInput;
import com.example.DemoGraphQL.filter.FilterInterface;
import com.example.DemoGraphQL.filter.resolver.scalar.*;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.SortField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RootResolver {

    private final static String TABLES = "com.example.DemoGraphQL.tables";

    List<Condition> conditions;

    private TableImplClassResolver tableImplClassResolver;

    RootResolver(TableImplClassResolver tableImplClassResolver)
    {
        this.conditions = new ArrayList<>();
        this.tableImplClassResolver = tableImplClassResolver;
    }

    /**
     * Recursively resolves the FilterInput tree starting from the root element to build the WHERE condition
     *
     * @param root        root element
     * @param filterInput the FilterInput object
     */
    public void resolve(TableImpl root, FilterInput filterInput) {
        if (filterInput == null) {
            return;
        }

        Condition scalarCondition = resolveScalar(root, filterInput);
        if (scalarCondition != null) {
            conditions.add(scalarCondition);
        }

        if(filterInput.getOr() != null) {
            RootResolver r = new Or(tableImplClassResolver);
            for(FilterInput or: filterInput.getOr()) {
                r.resolve(root, or);
            }
            conditions.add(r.getCondition());
        }

        if(filterInput.getAnd() != null) {
            RootResolver r = new And(tableImplClassResolver);
            for(FilterInput and: filterInput.getAnd()) {
                r.resolve(root, and);
            }
            conditions.add(r.getCondition());
        }
    }

    /**
     * returns the resolved condition for the WHERE clause and clears the resolver
     *
     * @return Condition
     */
    public Condition getCondition() {
        ArrayList<Condition> c = new ArrayList<>(conditions);
        conditions.clear();
        return DSL.and(c);
    }

    public SortField resolveOrderBy(String[] orderBy) {
        if (orderBy.length != 3) {
            return null;
        }
        String clazz;
        String field;
        String direction;
        clazz = Character.toString(orderBy[0].charAt(0)).toUpperCase() + orderBy[0].substring(1);
        field = orderBy[1];
        direction = orderBy[2];
        try {
            TableImpl tableImpl = (TableImpl) Class.forName(TABLES + "." + clazz).getConstructor().newInstance();
            Field tableField = tableImpl.field(field.toUpperCase());
            if ("ASC".equalsIgnoreCase(direction)) {
                return tableField.asc();
            }
            return tableField.desc();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Integer resolveLimit(Integer limit) {
        if (limit < 0) {
            return 0;
        }
        return limit;
    }

    /**
     * Resolves scalar comparision operators (e.g. eq, neq, etc...)
     *
     * @param root        root element
     * @param filterInput the FilterInput object
     */
    private Condition resolveScalar(TableImpl root, FilterInput filterInput){
        FilterInterface c = null;
        if(filterInput.getEq() != null) {
            c = new Eq(root, filterInput.getEq(), tableImplClassResolver);
        }
        else if(filterInput.getNe() != null) {
            c = new Ne(root, filterInput.getNe(), tableImplClassResolver);
        }
        else if(filterInput.getLike() != null) {
            c = new Like(root, filterInput.getLike(), tableImplClassResolver);
        }
        else if(filterInput.getLt() != null) {
            c = new Lt(root, filterInput.getLt(), tableImplClassResolver);
        }
        else if(filterInput.getIn() != null) {
            c = new In(root, filterInput.getIn(), tableImplClassResolver);
        }
        else if(filterInput.getNin() != null) {
            c = new Nin(root, filterInput.getNin(), tableImplClassResolver);
        }
        else if(filterInput.getBetween() != null) {
            c = new Between(root, filterInput.getBetween(), tableImplClassResolver);
        }
        else if(filterInput.getIsNull() != null) {
            c = new IsNull(root, filterInput.getIsNull(), tableImplClassResolver);
        }
        else if(filterInput.getNotNull() != null) {
            c = new NotNull(root, filterInput.getNotNull(), tableImplClassResolver);
        }

        return (c != null) ? c.getCondition() : null;
    }

}
