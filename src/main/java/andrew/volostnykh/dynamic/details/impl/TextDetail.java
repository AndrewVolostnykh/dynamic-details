package andrew.volostnykh.dynamic.details.impl;


import andrew.volostnykh.dynamic.details.DetailType;
import andrew.volostnykh.dynamic.details.Detail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextDetail implements Detail<String> {

    private Long id;
    private String value;
    private String name;
    private String code;

    @Override
    public DetailType getType() {
        return DetailType.TEXT;
    }
}
