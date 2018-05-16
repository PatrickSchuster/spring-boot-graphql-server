package com.example.DemoGraphQL.filter.resolver.scalar;

import com.example.DemoGraphQL.filter.MultiValueAbstractFilter;
import com.example.DemoGraphQL.filter.resolver.TableImplClassResolver;
import org.jooq.Condition;
import org.jooq.impl.TableImpl;

import java.util.List;

public class In extends MultiValueAbstractFilter
{
    public In(TableImpl root, List<String> param, TableImplClassResolver tableImplClassResolver) {
        super(root, param, tableImplClassResolver);
    }

    @Override
    public Condition getCondition()
    {
        if(field != null) {
            return field.in(values);
        }

        return null;
    }
}
