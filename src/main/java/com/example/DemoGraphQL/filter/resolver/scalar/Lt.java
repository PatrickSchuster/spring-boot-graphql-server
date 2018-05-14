package com.example.DemoGraphQL.filter.resolver.scalar;

import com.example.DemoGraphQL.filter.AbstractFilter;
import org.jooq.Condition;
import org.jooq.impl.TableImpl;

public class Lt extends AbstractFilter
{
    public Lt(TableImpl root, String attribute, String value) {
        super(root, attribute, value);
    }

    @Override
    public Condition getCondition()
    {
        if(field != null) {
            return field.lt(value);
        }

        return null;
    }
}
