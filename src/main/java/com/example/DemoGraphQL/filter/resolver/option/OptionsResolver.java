package com.example.DemoGraphQL.filter.resolver.option;

import com.example.DemoGraphQL.filter.resolver.TableImplClassResolver;
import org.jooq.Field;
import org.jooq.SortField;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OptionsResolver {

    private TableImplClassResolver tableImplClassResolver;

    public OptionsResolver(TableImplClassResolver tableImplClassResolver) {
        this.tableImplClassResolver = tableImplClassResolver;
    }

    public SortField[] resolveOrderBy(List<String> orderBy)
    {
        if (orderBy == null
            || orderBy.size() % 2 != 0)
        {
            return new SortField[0];
        }

        SortField[] fieldsArray = new SortField[orderBy.size()];
        String name;
        String direction;
        for (int i = 0; i < orderBy.size(); i += 2) {
            name = orderBy.get(i);
            direction = orderBy.get(i + 1);
            TableImplClassResolver.TableImplClassResolverResult tableImplClassResolverResult = tableImplClassResolver.resolve(name);
            Field tableField = tableImplClassResolverResult.root.field(tableImplClassResolverResult.root.field(tableImplClassResolverResult.field.toUpperCase()));
            SortField sortField;
            if ("ASC".equalsIgnoreCase(direction)) {
                sortField = tableField.asc();
            } else {
                sortField = tableField.desc();
            }
            fieldsArray[i] = sortField;
        }

        return fieldsArray;
    }

    public Integer resolveLimit(Integer limit)
    {
        if(limit == null) {
            return -1;
        }

        return limit;
    }
}
