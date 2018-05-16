package com.example.DemoGraphQL.filter.resolver;

import com.example.DemoGraphQL.filter.AbstractFilter;
import com.example.DemoGraphQL.filter.FilterInput;
import com.example.DemoGraphQL.filter.resolver.scalar.Eq;
import com.example.DemoGraphQL.filter.resolver.scalar.Like;
import com.example.DemoGraphQL.filter.resolver.scalar.Lt;
import com.example.DemoGraphQL.filter.resolver.scalar.Ne;
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

    RootResolver() {
        this.conditions = new ArrayList<>();
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

        if (filterInput.getOr() != null) {
            RootResolver r = new Or();
            for (FilterInput or : filterInput.getOr()) {
                r.resolve(root, or);
            }
            conditions.add(r.getCondition());
        }

        if (filterInput.getAnd() != null) {
            RootResolver r = new And();
            for (FilterInput and : filterInput.getAnd()) {
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
    private Condition resolveScalar(TableImpl root, FilterInput filterInput) {
        AbstractFilter c = null;
        if (filterInput.getEq() != null) {
            c = new Eq(root, filterInput.getEq().get(0), filterInput.getEq().get(1));
        } else if (filterInput.getNe() != null) {
            c = new Ne(root, filterInput.getNe().get(0), filterInput.getNe().get(1));
        } else if (filterInput.getLike() != null) {
            c = new Like(root, filterInput.getLike().get(0), filterInput.getLike().get(1));
        } else if (filterInput.getLt() != null) {
            c = new Lt(root, filterInput.getLt().get(0), filterInput.getLt().get(1));
        }

        return (c != null) ? c.getCondition() : null;
    }

}
