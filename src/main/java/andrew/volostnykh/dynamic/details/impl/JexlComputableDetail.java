package andrew.volostnykh.dynamic.details.impl;

import andrew.volostnykh.dynamic.details.Detail;
import andrew.volostnykh.dynamic.details.DetailType;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Development has been suspended according to bad performance tests
 * More information in test directory, performance.SpelVsJexl
 */
@Data
public class JexlComputableDetail<T> implements Detail<Object> {

    private Long id;
    private String formula;
    private String code;
    private String name;
    private Class<T> expectedReturnType;
    private Map<Long, Detail<?>> relatedDetails = new HashMap<>();
    // TODO: should it be like DB related entity?
    private Object sourceEntity;

    // TODO: figure out how to store stacklog
    private transient List<String> stackLog = new ArrayList<>();

    // TODO: to be implemented
    public T getValue() {
        return null;
    }

    public DetailType getType() {
        return DetailType.COMPUTABLE;
    }
}
