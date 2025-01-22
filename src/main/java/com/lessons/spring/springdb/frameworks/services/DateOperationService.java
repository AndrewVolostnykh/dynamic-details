package com.lessons.spring.springdb.frameworks.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("date")
// stores utilities for dates, useful for execution dates details
public class DateOperationService {

    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    public Boolean isAfter(String date, LocalDateTime earlier) {
        return LocalDateTime.parse(date).isAfter(earlier);
    }
}
