package com.example.DemoGraphQL.filter.resolver;

import com.example.DemoGraphQL.filter.FilterInput;
import org.jooq.Condition;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Resolver
{
    private List<Condition> conditions;

    private Resolver()
    {
        this.conditions = new ArrayList<>();
    }

    public void resolve(TableImpl root, FilterInput filterInput)
    {
        if(filterInput == null) {
            return;
        }

        if(filterInput.getEq() != null) {
            Condition c = new Eq(filterInput.getEq().getAttribute(), filterInput.getEq().getValue()).getCondition(root);
            if(c != null) {
                conditions.add(c);
            }

        }
        else if(filterInput.getNeq() != null) {
            Condition c = new Neq(filterInput.getNeq().getAttribute(), filterInput.getNeq().getValue()).getCondition(root);
            if(c != null) {
                conditions.add(c);
            }
        }

        if(filterInput.getOr() != null) {
            for(FilterInput or: filterInput.getOr()) {
                resolve(root, or);
            }
        }

        if(filterInput.getAnd() != null) {
            for(FilterInput and: filterInput.getAnd()) {
                resolve(root, and);
            }
        }
    }

    public List<Condition> getConditions() {
        ArrayList<Condition> c = new ArrayList<>(conditions);
        conditions.clear();
        return c;
    }
}
