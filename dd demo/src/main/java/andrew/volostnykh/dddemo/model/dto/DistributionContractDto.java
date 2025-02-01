package andrew.volostnykh.dddemo.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class DistributionContractDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal delayFine;
    private LocalDateTime startDate;
    private LocalDateTime deadLine;
    private BigDecimal depreciationRate;
    private BigDecimal insuranceRate;

    private List<DetailDto> details;
}
