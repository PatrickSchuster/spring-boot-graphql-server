package com.example.DemoGraphQL.filter;

import com.example.DemoGraphQL.filter.resolver.Eq;
import org.springframework.stereotype.Component;

@Component
public class FilterFactory {

    public FilterInterface getInstance(FilterInput filterInput) throws Exception
    {
        switch(filterInput.getOperator()) {
            case "=":
                return new Eq(filterInput.getAttribute(), filterInput.getValue());
            default:
                throw new Exception("invalid operator type: "+filterInput.getOperator());
        }
    }
}
