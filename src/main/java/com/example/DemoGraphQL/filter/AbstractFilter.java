package com.example.DemoGraphQL.filter;

import com.example.DemoGraphQL.filter.resolver.TableImplClassResolver;
import lombok.Getter;
import lombok.Setter;
import org.jooq.Field;
import org.jooq.impl.TableImpl;

import java.util.List;

public abstract class AbstractFilter implements FilterInterface
{
    @Getter
    @Setter
    protected String value;

    protected Field field;

    public AbstractFilter(TableImpl root, List<String> param, TableImplClassResolver tableImplClassResolver)
    {
        this.value = param.get(1);

        String field = param.get(0);

        TableImplClassResolver.TableImplClassResolverResult r = tableImplClassResolver.resolve(field, root);
        root = r.root;
        this.field = root.field(r.field.toUpperCase());
    }
}
