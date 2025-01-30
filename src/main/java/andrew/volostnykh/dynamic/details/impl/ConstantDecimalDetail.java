package andrew.volostnykh.dynamic.details.impl;

import andrew.volostnykh.dynamic.details.DetailType;
import andrew.volostnykh.dynamic.details.DecimalDetail;
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