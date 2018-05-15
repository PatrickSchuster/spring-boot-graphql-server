package com.example.DemoGraphQL.filter.resolver;

import com.example.DemoGraphQL.filter.AbstractFilter;
import com.example.DemoGraphQL.filter.FilterInput;
import com.example.DemoGraphQL.filter.resolver.scalar.Eq;
import com.example.DemoGraphQL.filter.resolver.scalar.Like;
import com.example.DemoGraphQL.filter.resolver.scalar.Lt;
import com.example.DemoGraphQL.filter.resolver.scalar.Ne;
import org.jooq.Condition;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RootResolver
{
    List<Condition> conditions;

    private TableImplClassResolver tableImplClassResolver;

    RootResolver(TableImplClassResolver tableImplClassResolver)
    {
        this.conditions = new ArrayList<>();
        this.tableImplClassResolver = tableImplClassResolver;
    }

    /**
     * Recursively resolves the FilterInput tree starting from the root element to build the WHERE condition
     * @param root          root element
     * @param filterInput   the FilterInput object
     */
    public void resolve(TableImpl root, FilterInput filterInput)
    {
        if(filterInput == null) {
            return;
        }

        Condition scalarCondition = resolveScalar(root, filterInput);
        if(scalarCondition != null) {
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
     * @return Condition
     */
    public Condition getCondition()
    {
        ArrayList<Condition> c = new ArrayList<>(conditions);
        conditions.clear();
        return DSL.and(c);
    }

    /**
     * Resolves scalar comparision operators (e.g. eq, neq, etc...)
     * @param root          root element
     * @param filterInput   the FilterInput object
     */
    private Condition resolveScalar(TableImpl root, FilterInput filterInput)
    {
        AbstractFilter c = null;
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

        return (c != null) ? c.getCondition() : null;
    }
}
