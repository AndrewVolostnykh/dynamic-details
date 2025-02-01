package andrew.volostnykh.dddemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "details")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;

    private String value;
    @Enumerated(EnumType.STRING)
    private DetailValueType valueType;

    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private ChronoUnit chronoUnit;

    private String formula;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<LocalDateTime, BigDecimal> periodicData;

    @Transient
    @JsonIgnore
    private transient List<String> stackLog;
}
