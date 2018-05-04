package com.example.DemoGraphQL.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AllPurposeFilter {

    private String firstProperty;
    private String secondProperty;
    private String operator;

    @JsonProperty("firstProperty") // the name must match the schema
    public String getFirstProperty() {
        return firstProperty;
    }

    @JsonProperty("secondProperty") // the name must match the schema
    public String getSecondProperty() {
        return secondProperty;
    }

    @JsonProperty("operator") // the name must match the schema
    public String getOperator() {
        return operator;
    }

}
