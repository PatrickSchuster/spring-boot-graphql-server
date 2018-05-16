package com.example.DemoGraphQL.filter;

import com.example.DemoGraphQL.filter.resolver.TableImplClassResolver;
import org.jooq.Field;
import org.jooq.impl.TableImpl;

abstract public class FieldOnlyAbstractFilter implements FilterInterface
{
    protected Field field;

    public FieldOnlyAbstractFilter(TableImpl root, String field, TableImplClassResolver tableImplClassResolver)
    {
        TableImplClassResolver.TableImplClassResolverResult r = tableImplClassResolver.resolve(field, root);
        root = r.root;
        this.field = root.field(r.field.toUpperCase());
    }
}
