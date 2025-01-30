package andrew.volostnykh.dynamic.details.impl;

import andrew.volostnykh.dynamic.details.DecimalDetail;
import andrew.volostnykh.dynamic.details.DetailType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
public class PeriodicDetail implements DecimalDetail {

    private Long id;
    private String code;
    private String name;

    private LocalDate date;
    private ChronoUnit chronoUnit;

    private BigDecimal value;

    @Override
    public DetailType getType() {
        return DetailType.PERIODIC;
    }
}
