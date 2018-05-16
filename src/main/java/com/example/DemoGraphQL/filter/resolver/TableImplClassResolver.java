package com.example.DemoGraphQL.filter.resolver;

import org.jooq.impl.TableImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class TableImplClassResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableImpl.class);

    // TODO: make configurable
    private static final String PACKAGE = "com.example.DemoGraphQL.tables";

    public class TableImplClassResolverResult {
        public TableImpl root;
        public String field;
    }

    public TableImplClassResolverResult resolve(String name) {
        return resolve(name, null);
    }

    public TableImplClassResolverResult resolve(String name, TableImpl root) {
        String[] parts = name.split("\\.");
        String clazz = PACKAGE + "." + parts[0];

        TableImplClassResolverResult result = new TableImplClassResolverResult();
        result.root = root;
        result.field = name;

        if (parts.length == 1) {
            return result;
        }

        try {
            result.field = parts[1];
            result.root = (TableImpl) Class.forName(clazz).getConstructor().newInstance();
            return result;
        } catch (ClassNotFoundException e) {
            LOGGER.error("Class {} not found. Detailed message: {}", clazz, e);
        } catch (NoSuchMethodException e) {
            LOGGER.error("No such method. Detailed message: {}", e);
        } catch (InstantiationException e) {
            LOGGER.error("InstantionatException caught. Detailed message: {}", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException caught. Detailed message: {}", e);
        } catch (InvocationTargetException e) {
            LOGGER.error("InvocationTargetException caught. Detailed message: {}", e);
        }

        return result;
    }
}
