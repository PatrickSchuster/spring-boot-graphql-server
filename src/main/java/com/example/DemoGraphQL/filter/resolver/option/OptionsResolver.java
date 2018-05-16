package com.example.DemoGraphQL.filter.resolver.option;

import com.example.DemoGraphQL.filter.resolver.TableImplClassResolver;
import org.jooq.Field;
import org.jooq.SortField;
import org.springframework.stereotype.Component;

@Component
public class OptionsResolver {

    private TableImplClassResolver tableImplClassResolver;

    public OptionsResolver(TableImplClassResolver tableImplClassResolver) {
        this.tableImplClassResolver = tableImplClassResolver;
    }

    public SortField resolveOrderBy(String[] orderBy) {
        if (orderBy.length != 2) {
            return null;
        }
        String name;
        String direction;
        name = orderBy[0];
        direction = orderBy[1];
        TableImplClassResolver.TableImplClassResolverResult tableImplClassResolverResult = tableImplClassResolver.resolve(name);
        Field tableField = tableImplClassResolverResult.root.field(tableImplClassResolverResult.root.field(tableImplClassResolverResult.field.toUpperCase()));
        if(tableField == null){
            return null;
        }
        if ("ASC".equalsIgnoreCase(direction)) {
            return tableField.asc();
        }
        return tableField.desc();
    }

    public Integer resolveLimit(Integer limit) {
        if (limit < 0) {
            return 0;
        }
        return limit;
    }

}
