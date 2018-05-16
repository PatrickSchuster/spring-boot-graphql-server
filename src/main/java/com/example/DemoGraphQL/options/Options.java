package com.example.DemoGraphQL.options;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Options {

    private String[] orderBy;
    private Integer limit;

    @JsonProperty("orderBy")
    public String[] getOrderBy() {
        return orderBy;
    }

    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

}