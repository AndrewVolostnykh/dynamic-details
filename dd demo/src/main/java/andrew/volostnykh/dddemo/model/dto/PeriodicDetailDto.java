package andrew.volostnykh.dddemo.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class PeriodicDetailDto {
    private Map<LocalDateTime, BigDecimal> periodicTable;
    private BigDecimal total;
    private Long count;
    // etc

}
