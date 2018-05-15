package com.example.DemoGraphQL.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class FilterInput extends ScalarFilterInput
{
    @Getter
    @Setter
    protected List<FilterInput> and;

    @Getter
    @Setter
    protected List<FilterInput> or;
}
