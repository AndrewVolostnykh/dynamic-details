package andrew.volostnykh.dynamic.details.impl;

import andrew.volostnykh.dynamic.details.DetailType;
import andrew.volostnykh.dynamic.details.Detail;
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
public class DateDetail implements Detail<LocalDateTime> {

    private Long id;
    private String name;
    private LocalDateTime value;
    private String code;

    @Override
    public DetailType getType() {
        return DetailType.DATE;
    }
}
