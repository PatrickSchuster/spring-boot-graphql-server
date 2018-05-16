package com.example.DemoGraphQL.filter.resolver.scalar;

import com.example.DemoGraphQL.filter.FieldOnlyAbstractFilter;
import com.example.DemoGraphQL.filter.resolver.TableImplClassResolver;
import org.jooq.Condition;
import org.jooq.impl.TableImpl;

public class IsNull extends FieldOnlyAbstractFilter
{
    public IsNull(TableImpl root, String field, TableImplClassResolver tableImplClassResolver) {
        super(root, field, tableImplClassResolver);
    }

    @Override
    public Condition getCondition()
    {
        if(field != null) {
            return field.isNull();
        }

        return null;
    }
}
