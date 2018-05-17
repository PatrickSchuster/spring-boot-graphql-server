package com.example.DemoGraphQL.options;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Options
{
    @Getter
    @Setter
    private List<String> orderBy;

    @Getter
    @Setter
    private Integer limit;
}