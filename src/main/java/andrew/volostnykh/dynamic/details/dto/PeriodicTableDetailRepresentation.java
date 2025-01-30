package andrew.volostnykh.dynamic.details.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PeriodicTableDetailRepresentation {

    LocalDate getStartDate();
    LocalDate getEndDate();
    BigDecimal getValue();
}
