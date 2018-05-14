package com.example.DemoGraphQL.filter;

import org.jooq.Condition;

public interface FilterInterface {
    Condition getCondition();
}
