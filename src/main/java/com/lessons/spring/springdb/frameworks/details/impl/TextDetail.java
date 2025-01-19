package com.lessons.spring.springdb.frameworks.details.impl;


import com.lessons.spring.springdb.frameworks.details.DetailType;
import com.lessons.spring.springdb.frameworks.details.DetailEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextDetail implements DetailEntry<String> {

    private Long id;
    private String value;
    private String name;
    private String code;

    @Override
    public DetailType getType() {
        return DetailType.TEXT;
    }
}
