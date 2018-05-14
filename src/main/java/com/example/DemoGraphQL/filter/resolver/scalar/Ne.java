package com.example.DemoGraphQL.filter.resolver.scalar;

import com.example.DemoGraphQL.filter.AbstractFilter;
import org.jooq.Condition;
import org.jooq.impl.TableImpl;

public class Ne extends AbstractFilter
{
    public Ne(TableImpl root, String attribute, String value) {
        super(root, attribute, value);
    }

    @Override
    public Condition getCondition()
    {
        if(field != null) {
            return field.ne(value);
        }

        return null;
    }
}
