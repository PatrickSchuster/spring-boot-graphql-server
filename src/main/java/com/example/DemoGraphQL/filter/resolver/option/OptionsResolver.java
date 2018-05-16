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

    public List<SortField> resolveOrderBy(String[] orderBy) {
        if (orderBy.length % 2 != 0) {
            return null;
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
        return fields;
    }

    public Integer resolveLimit(Integer limit) {
        if (limit < 0) {
            return 0;
        }
        return limit;
    }

}
