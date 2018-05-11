package com.example.DemoGraphQL.filter.resolver;

import com.example.DemoGraphQL.filter.AbstractFilter;
import org.jooq.Condition;
import org.jooq.impl.TableImpl;

public class Neq extends AbstractFilter
{
    public Neq(String attribute, String value) {
        super(attribute, value);
    }

    @Override
    public Condition getCondition(TableImpl root) {
        return null;
    }
}
