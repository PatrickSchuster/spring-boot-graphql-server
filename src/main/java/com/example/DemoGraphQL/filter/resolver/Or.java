package com.example.DemoGraphQL.filter.resolver;

import org.jooq.Condition;
import org.jooq.impl.DSL;

public class Or extends RootResolver
{
    public Or(TableImplClassResolver tableImplClassResolver) {
        super(tableImplClassResolver);
    }

    @Override
    public Condition getCondition() {
        return DSL.or(conditions);
    }
}
