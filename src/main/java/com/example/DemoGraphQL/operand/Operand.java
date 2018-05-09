package com.example.DemoGraphQL.operand;

import com.example.DemoGraphQL.filter.Filter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Operand {

    private String operand;
    private List<Filter> filters;

    @JsonProperty("operand")
    public String getOperand(){
        return this.operand;
    }

    @JsonProperty("filters")
    public List<Filter> getFilters(){
        return this.filters;
    }

}
