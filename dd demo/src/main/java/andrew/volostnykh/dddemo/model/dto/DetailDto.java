package andrew.volostnykh.dddemo.model.dto;

import andrew.volostnykh.dddemo.model.DetailValueType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

// TODO: split for several classes with different implementations
@Data
@Builder
public class DetailDto {
    private Long id;

    private String code;
    private String name;
    private String description;

    private String value;
    private DetailValueType valueType;

    private LocalDateTime date;
    private ChronoUnit chronoUnit;

    private String formula;
}
