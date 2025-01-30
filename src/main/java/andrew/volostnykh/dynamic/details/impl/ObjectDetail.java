package andrew.volostnykh.dynamic.details.impl;

import com.fasterxml.jackson.databind.JsonNode;
import andrew.volostnykh.dynamic.details.DetailType;
import andrew.volostnykh.dynamic.details.Detail;
import lombok.Data;

@Data
public class ObjectDetail implements Detail<JsonNode> {

    private Long id;
    private String name;
    private String code;
    private JsonNode value;

    @Override
    public DetailType getType() {
        return DetailType.OBJECT;
    }
}
