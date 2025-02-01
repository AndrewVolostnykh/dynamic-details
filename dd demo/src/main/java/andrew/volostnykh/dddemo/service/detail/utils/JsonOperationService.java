package andrew.volostnykh.dddemo.service.detail.utils;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service("json")
public class JsonOperationService {
    public Object path(Map<String, Object> json, String... path) {

        for (String s : path) {
            System.err.println(s);
        }
        return null;
    }
}
