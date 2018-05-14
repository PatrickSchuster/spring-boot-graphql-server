package com.example.DemoGraphQL.filter;

import lombok.Getter;
import lombok.Setter;
import org.jooq.Field;
import org.jooq.impl.TableImpl;

public abstract class AbstractFilter implements FilterInterface
{
    @Getter
    @Setter
    protected String value;

    protected Field field;

    public AbstractFilter(TableImpl root, String attribute, String value)
    {
        this.value = value;
        this.field = root.field(attribute.toUpperCase());
    }
}
