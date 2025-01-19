package com.lessons.spring.springdb.frameworks.details.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.lessons.spring.springdb.frameworks.details.DetailType;
import com.lessons.spring.springdb.frameworks.details.DetailEntry;
import lombok.Data;

@Data
public class ObjectDetail implements DetailEntry<JsonNode> {

    private Long id;
    private String name;
    private String code;
    private JsonNode value;

    @Override
    public DetailType getType() {
        return DetailType.OBJECT;
    }
}
