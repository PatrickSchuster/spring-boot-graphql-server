package com.example.DemoGraphQL.filter.resolver;

import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Service;

@Service
public class TableImplClassResolver
{
    public class TableImplClassResolverResult {
        public TableImpl root;
        public String field;
    }

    // TODO: make configurable
    private static final String PACKAGE = "com.example.DemoGraphQL.tables";

    public TableImplClassResolverResult resolve(String name) {
        return resolve(name, null);
    }

    public TableImplClassResolverResult resolve(String name, TableImpl root)
    {
        String[] parts = name.split("\\.");

        TableImplClassResolverResult result = new TableImplClassResolverResult();
        result.root = root;
        result.field = name;

        if(parts.length == 1) {
            return result;
        }

        try {
            result.field = parts[1];
            result.root = (TableImpl) Class.forName(PACKAGE+"."+parts[0]).getConstructor().newInstance();
            return result;
        }
        catch (Exception e) {
            // TODO: log me
        }
        catch (Error e) {
            // TODO: log me
        }

        return result;
    }
}
