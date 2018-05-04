package com.example.DemoGraphQL.filter;

import lombok.Getter;
import lombok.Setter;

public class FilterInput
{
    @Getter
    @Setter
    protected String attribute;

    @Getter
    @Setter
    protected String operator;

    @Getter
    @Setter
    protected String value;
}
