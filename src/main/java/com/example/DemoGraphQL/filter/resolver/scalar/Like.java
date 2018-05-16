package com.example.DemoGraphQL.filter.resolver.scalar;

import com.example.DemoGraphQL.filter.AbstractFilter;
import com.example.DemoGraphQL.filter.resolver.TableImplClassResolver;
import org.jooq.Condition;
import org.jooq.impl.TableImpl;

import java.util.List;

public class Like extends AbstractFilter
{
    public Like(TableImpl root, List<String> param, TableImplClassResolver tableImplClassResolver) {
        super(root, param, tableImplClassResolver);
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
