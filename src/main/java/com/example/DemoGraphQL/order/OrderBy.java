package com.example.DemoGraphQL.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderBy {

    private String field;
    private String direction;

    @JsonProperty("field")
    public String getField() {
        return field;
    }

    @JsonProperty("direction")
    public String getDirection() {
        return direction;
    }

}
