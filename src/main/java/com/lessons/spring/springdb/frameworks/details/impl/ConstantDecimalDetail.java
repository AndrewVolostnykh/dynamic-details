package com.lessons.spring.springdb.frameworks.details.impl;

import com.lessons.spring.springdb.frameworks.details.DetailType;
import com.lessons.spring.springdb.frameworks.details.DecimalDetail;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConstantDecimalDetail implements DecimalDetail {

    private Long id;
    private String name;
    private String code;
    private BigDecimal value;

    @Override
    public DetailType getType() {
        return DetailType.BIG_DECIMAL;
    }
}