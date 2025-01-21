package com.lessons.spring.springdb.frameworks.services.context;

import lombok.RequiredArgsConstructor;
import org.springframework.expression.BeanResolver;
import org.springframework.stereotype.Component;

import java.util.Map;

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
