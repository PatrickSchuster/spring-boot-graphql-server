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
}
