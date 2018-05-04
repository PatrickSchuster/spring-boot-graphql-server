package com.example.DemoGraphQL.filter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorFilter {

    private String firstNameContains;
    private String lastNameContains;

    @JsonProperty("firstName_contains") // the name must match the schema
    public String getFirstNameContains(){
        return firstNameContains;
    }

    public void setFirstNameContains(String firstNameContains){
        this.firstNameContains = firstNameContains;
    }

    @JsonProperty("lastName_contains") // the name must match the schema
    public String getLastNameContains(){
        return lastNameContains;
    }

    public void setLastNameContains(String lastNameContains){
        this.lastNameContains = lastNameContains;
    }

}
