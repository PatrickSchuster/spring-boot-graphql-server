package com.example.DemoGraphQL.filter.resolver.scalar;

import com.example.DemoGraphQL.filter.MultiValueAbstractFilter;
import com.example.DemoGraphQL.filter.resolver.TableImplClassResolver;
import org.jooq.Condition;
import org.jooq.impl.TableImpl;

import java.util.List;

public class Between extends MultiValueAbstractFilter
{
    public Between(TableImpl root, List<String> param, TableImplClassResolver tableImplClassResolver) {
        super(root, param, tableImplClassResolver);
    }

    @Override
    public Condition getCondition()
    {
        if(field != null) {
            return field.between(values.get(0), values.get(1));
        }

        return null;
    }
}
