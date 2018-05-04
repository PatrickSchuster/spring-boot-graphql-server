package com.example.DemoGraphQL.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
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

    @Getter
    @Setter
    protected List<FilterInput> and;

    @Getter
    @Setter
    protected List<FilterInput> or;
}
