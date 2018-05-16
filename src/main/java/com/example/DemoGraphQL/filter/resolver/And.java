package com.example.DemoGraphQL.filter.resolver;

import org.jooq.Condition;
import org.jooq.impl.DSL;

public class And extends RootResolver
{
    public And(TableImplClassResolver tableImplClassResolver) {
        super(tableImplClassResolver);
    }

    @Override
    public Condition getCondition()
    {
        return DSL.and(conditions);
    }
}
