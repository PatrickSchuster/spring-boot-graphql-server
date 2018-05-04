package com.example.DemoGraphQL.operand;

import com.example.DemoGraphQL.filter.AllPurposeFilter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Operand {

    private String operand;
    private List<AllPurposeFilter> filters;

    @JsonProperty("operand")
    public String getOperand(){
        return this.operand;
    }

    @JsonProperty("filters")
    public List<AllPurposeFilter> getFilters(){
        return this.filters;
    }

}
