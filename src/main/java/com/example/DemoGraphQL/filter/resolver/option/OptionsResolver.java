package com.example.DemoGraphQL.filter.resolver.option;

import com.example.DemoGraphQL.filter.resolver.TableImplClassResolver;
import org.jooq.Field;
import org.jooq.SortField;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OptionsResolver {

    private TableImplClassResolver tableImplClassResolver;

    public OptionsResolver(TableImplClassResolver tableImplClassResolver) {
        this.tableImplClassResolver = tableImplClassResolver;
    }

    public SortField[] resolveOrderBy(String[] orderBy)
    {
        if (orderBy == null
            || orderBy.length % 2 != 0)
        {
            return new SortField[0];
        }

        List<SortField> fields = new ArrayList<>();
        String name;
        String direction;
        for (int i = 0; i < orderBy.length; i += 2) {
            name = orderBy[i];
            direction = orderBy[i + 1];
            TableImplClassResolver.TableImplClassResolverResult tableImplClassResolverResult = tableImplClassResolver.resolve(name);
            Field tableField = tableImplClassResolverResult.root.field(tableImplClassResolverResult.root.field(tableImplClassResolverResult.field.toUpperCase()));
            SortField sortField;
            if ("ASC".equalsIgnoreCase(direction)) {
                sortField = tableField.asc();
            } else {
                sortField = tableField.desc();
            }
            fields.add(sortField);
        }

        return fields.toArray(new SortField[0]);
    }

    public Integer resolveLimit(Integer limit)
    {
        if(limit == null) {
            return -1;
        }

        return limit;
    }
}
