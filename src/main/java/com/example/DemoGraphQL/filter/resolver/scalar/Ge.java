package com.example.DemoGraphQL.filter.resolver.scalar;

import com.example.DemoGraphQL.filter.AbstractFilter;
import com.example.DemoGraphQL.filter.resolver.TableImplClassResolver;
import org.jooq.Condition;
import org.jooq.impl.TableImpl;

import java.util.List;

public class Ge extends AbstractFilter
{
    public Ge(TableImpl root, List<String> param, TableImplClassResolver tableImplClassResolver) {
        super(root, param, tableImplClassResolver);
    }

    @Override
    public Condition getCondition()
    {
        if(field != null) {
            return field.greaterOrEqual(value);
        }

        return null;
    }
}
