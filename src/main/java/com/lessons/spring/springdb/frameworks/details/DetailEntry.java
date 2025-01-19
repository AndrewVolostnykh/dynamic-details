package com.lessons.spring.springdb.frameworks.details;

public interface DetailEntry<T> {

    Long getId();
    T getValue();
     String getName();
     String getCode();
     DetailType getType();

}
