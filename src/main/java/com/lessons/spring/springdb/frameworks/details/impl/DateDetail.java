package com.lessons.spring.springdb.frameworks.details.impl;

import com.lessons.spring.springdb.frameworks.DetailType;
import com.lessons.spring.springdb.frameworks.details.DetailEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// timezone field
// format field
// operations like plus day or week day of the date
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateDetail implements DetailEntry<LocalDateTime> {

    private Long id;
    private String name;
    private LocalDateTime value;
    private String code;

    @Override
    public DetailType getType() {
        return DetailType.DATE;
    }
}
