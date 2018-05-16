package com.example.DemoGraphQL.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ScalarFilterInput
{
    @Getter
    @Setter
    protected List<String> eq;

    @Getter
    @Setter
    protected List<String> ne;

    @Getter
    @Setter
    protected List<String> like;

    @Getter
    @Setter
    protected List<String> lt;

    @Getter
    @Setter
    protected List<String> in;

    @Getter
    @Setter
    private List<String> nin;

    @Getter
    @Setter
    private List<String> between;

    @Getter
    @Setter
    private String isNull;

    @Getter
    @Setter
    private String notNull;
}
