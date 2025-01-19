package com.lessons.spring.springdb.frameworks.details.impl;

import com.lessons.spring.springdb.frameworks.details.DetailEntry;
import com.lessons.spring.springdb.frameworks.DetailType;
import lombok.Data;

import java.util.Map;

// use Python or shall to execute some more complicated operations than just computation simple formula
// TODO: what the return type should be?
// TODO: should it not execute value even for visualization?
// TODO: but only when user push button 'execute'?
@Data
public class PythonExecutableDetail implements DetailEntry<Map<String, Object>> {
    private Long id;
    private Map<String, Object> value;
    // params? how to get related objects?
    private String name;
    private String code;

    public DetailType getType() {
        return DetailType.EXECUTABLE;
    }
}
