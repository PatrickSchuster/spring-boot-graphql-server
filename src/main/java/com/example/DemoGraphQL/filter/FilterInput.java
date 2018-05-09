package com.example.DemoGraphQL.filter;

import com.example.DemoGraphQL.filter.model.Eq;
import com.example.DemoGraphQL.filter.model.Neq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class FilterInput
{
    @Getter
    @Setter
    protected Eq eq;

    @Getter
    @Setter
    protected Neq neq;

    @Getter
    @Setter
    protected List<FilterInput> and;

    @Getter
    @Setter
    protected List<FilterInput> or;
}
