package com.example.DemoGraphQL.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public abstract class AbstractFilter implements FilterInterface
{
    @Getter
    @Setter
    protected String attribute;

    @Getter
    @Setter
    protected String value;
}
