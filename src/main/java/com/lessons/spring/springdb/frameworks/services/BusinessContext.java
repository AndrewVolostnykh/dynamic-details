package com.lessons.spring.springdb.frameworks.services;

import lombok.RequiredArgsConstructor;
import org.springframework.expression.BeanResolver;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BusinessContext {

    private final DetailsBeanResolver beanResolver;
    // TODO: service for querying global vars and other business data

    public BeanResolver getBeanResolver() {
        return beanResolver;
    }

    public Map<String, Object> getGlobalVars() {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
