package com.example.DemoGraphQL.filter.model;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractModel
{
    @Getter
    @Setter
    protected String attribute;

    @Getter
    @Setter
    protected String value;
}
