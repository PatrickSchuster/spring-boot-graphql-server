package com.example.DemoGraphQL.filter;

import org.jooq.Condition;
import org.jooq.impl.TableImpl;

public interface FilterInterface {
    Condition getCondition(TableImpl root);
}
