package com.example.DemoGraphQL.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Filter {

    private String operand;
    private String key;
    private String value;
    private String operator;
    /*
    private List<Filter> and;
    private List<Filter> or;
    */

    @JsonProperty("operand")
    public String getOperand() {
        return operand;
    }

    @JsonProperty("key") // the name must match the schema
    public String getKey() {
        return key;
    }

    @JsonProperty("value") // the name must match the schema
    public String getValue() {
        return value;
    }

    @JsonProperty("operator") // the name must match the schema
    public String getOperator() {
        return operator;
    }

    /*
    @JsonProperty("and")
    public List<Filter> getAnd() {
        return and;
    }

    @JsonProperty("or")
    public List<Filter> getOr() {
        return or;
    }
    */

}
