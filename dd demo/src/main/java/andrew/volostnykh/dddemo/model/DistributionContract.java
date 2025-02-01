package andrew.volostnykh.dddemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static andrew.volostnykh.dddemo.model.DistributionContract.DISTRIBUTION_CONTRACTS;

@Builder
@Data
@Entity
@Table(name = DISTRIBUTION_CONTRACTS)
@NoArgsConstructor
@AllArgsConstructor
public class DistributionContract implements Model {
    public static final String DISTRIBUTION_CONTRACTS = "distribution_contracts";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal delayFine;
    private LocalDateTime startDate;
    private LocalDateTime deadLine;
    private BigDecimal depreciationRate;
    private BigDecimal insuranceRate;

    @Override
    public String getEntityName() {
        return DISTRIBUTION_CONTRACTS;
    }
}
