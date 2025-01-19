package com.lessons.spring.springdb.frameworks.details;

import com.lessons.spring.springdb.frameworks.DetailType;

public interface DetailEntry<T> {

    Long getId();
    T getValue();
     String getName();
     String getCode();
     DetailType getType();

}
