package com.lessons.spring.springdb.frameworks.services;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service("json")
public class JsonOperationService {
    public Object path(Map<String, Object> json, String... path) {

        for (String s : path) {
            System.err.println(s);
        }
        return null;
    }
}
