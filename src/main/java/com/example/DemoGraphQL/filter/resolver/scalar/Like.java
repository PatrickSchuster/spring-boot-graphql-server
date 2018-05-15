package com.example.DemoGraphQL.filter.resolver.scalar;

import com.example.DemoGraphQL.filter.AbstractFilter;
import org.jooq.Condition;
import org.jooq.impl.TableImpl;

public class Like extends AbstractFilter
{
    public Like(TableImpl root, String attribute, String value) {
        super(root, attribute, value);
    }

    @Override
    public Condition getCondition()
    {
        if(field != null) {
            return field.like(value);
        }

        return null;
    }
}
