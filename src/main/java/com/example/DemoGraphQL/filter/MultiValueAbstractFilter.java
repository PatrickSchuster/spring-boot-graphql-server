package com.example.DemoGraphQL.filter;

import com.example.DemoGraphQL.filter.resolver.TableImplClassResolver;
import org.jooq.impl.TableImpl;

import java.util.List;

abstract public class MultiValueAbstractFilter extends AbstractFilter
{
    protected List<String> values;

    public MultiValueAbstractFilter(TableImpl root, List<String> param, TableImplClassResolver tableImplClassResolver) {
        super(root, param, tableImplClassResolver);
        // TODO: param needs to be verified
        this.values = param.subList(1, param.size());
    }
}
