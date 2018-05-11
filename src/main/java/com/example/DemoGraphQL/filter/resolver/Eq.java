package com.example.DemoGraphQL.filter.resolver;

import com.example.DemoGraphQL.filter.AbstractFilter;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.TableImpl;

public class Eq extends AbstractFilter
{
    public Eq(String attribute, String value) {
        super(attribute, value);
    }

    @Override
    public Condition getCondition(TableImpl root)
    {
        Field field = root.field(attribute.toUpperCase());
        if(field != null) {
            return field.eq(value);
        }

        return null;
    }
}
