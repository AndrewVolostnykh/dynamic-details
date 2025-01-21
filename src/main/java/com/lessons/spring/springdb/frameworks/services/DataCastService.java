package com.lessons.spring.springdb.frameworks.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("cast")
public class DataCastService {

    public BigDecimal toNumber(String number) {
        // should it be parse Double?
        return new BigDecimal(number);
    }

    public BigDecimal toNumber(Integer number) {
        return BigDecimal.valueOf(number);
    }
}
