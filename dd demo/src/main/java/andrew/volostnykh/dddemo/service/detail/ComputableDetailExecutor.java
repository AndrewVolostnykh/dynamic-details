package andrew.volostnykh.dddemo.service.detail;

import andrew.volostnykh.dddemo.core.exceptions.DetailProcessException;
import andrew.volostnykh.dddemo.model.Detail;
import andrew.volostnykh.dddemo.model.DetailValueType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComputableDetailExecutor {
    public static final String ALIAS_REPLACING_PATTERN = "<\\w+>";
    private static final Pattern PATTERN = Pattern.compile(ALIAS_REPLACING_PATTERN);

    private Long id;
    private String formula;

    private Map<String, Detail> relatedDetails = new HashMap<>();
    private Object sourceEntity;
    private Detail detail;

    private List<String> stackLog = new ArrayList<>();
    private DetailProcessorContext detailProcessorContext;

    public ComputableDetailExecutor(
            Detail detail,
            Map<String, Detail> relatedCodeDetail,
            Object sourceEntity,
            DetailProcessorContext detailProcessorContext
    ) {
        this.detail = detail;
        this.id = detail.getId();
        this.formula = detail.getFormula();
        this.relatedDetails = relatedCodeDetail;
        this.sourceEntity = sourceEntity;
        this.detailProcessorContext = detailProcessorContext;
    }

    public String getValue() {
        try {
            if (detailProcessorContext == null) {
                throw new RuntimeException(String.format("DetailProcessorContext is null for computable detail: %s", id));
            }

            stackLog.add(String.format("Computing %s with formula %s", id, formula));
            ComputableExecutionContext context = new ComputableExecutionContext(
                    detail,
                    sourceEntity,
                    detailProcessorContext
            );
            SpelExpressionParser parser = new SpelExpressionParser();
            String preparedFormula = prepareFormulaToExecution();
            stackLog.add(String.format("Prepared formula [%s]", preparedFormula));
            String rawValue = parser.parseExpression(preparedFormula).getValue(context, String.class);

            if (rawValue != null) {
                stackLog.add("Result value: " + rawValue);
                // TODO: return only allowed types: string, big decimal, map, list
                return rawValue;
            }
        } catch (DetailProcessException dpe) {
            String message = String.format(
                    ">>>> Detail computation failed: %s. Formula: %s; Detail id: %s",
                    dpe.getMessage(),
                    this.formula,
                    id
            );
            stackLog.add(message);
            throw new RuntimeException(message);
        }

        String message = String.format(
                ">>>> Detail computation failed. Detail did not return value. Formula: %s; Detail id: %s",
                this.formula,
                id
        );
        stackLog.add(message);
        throw new RuntimeException(message);
    }

    private Object getDetailValue(Detail detail) {
        if (detail.getValueType() == DetailValueType.COMPUTABLE) {
            stackLog.add(String.format("Sub-detail id=[%s] stack log", detail.getId()));
            if (detail.getStackLog() != null) {
                stackLog.addAll(detail.getStackLog());
            }
            return new ComputableDetailExecutor(
                        detail,
                        relatedDetails,
                        sourceEntity,
                        detailProcessorContext)
                    .getValue();
        }

        return detail.getValue();
    }
    public String prepareFormulaToExecution() {
        Map<String, Object> result = new HashMap<>();
        Matcher matcher = PATTERN.matcher(formula);
        while (matcher.find()) {
            // FIXME: CRITICAL BUG!!! Next line will remove all '<' and '>' symbols from expression!
            String relatedDetailId = matcher.group().replaceAll("[\\<\\>]", "");
            Detail detail = relatedDetails.get(relatedDetailId);
            String group = matcher.group();
            Object value = getDetailValue(detail);
            if (value instanceof Map) {
                value = toJson(value);
            }
            stackLog.add(String.format("Detected detail sub-value: id=[%s], value=[%s]", group, value));
            result.put(
                    matcher.group(),
                    value
            );

        }

        for (Map.Entry<String, Object> entry : result.entrySet()) {
            formula = formula.replace(
                    entry.getKey(),
                    entry.getValue().toString()
            );
        }

        return formula;
    }

    // TODO: correct exception processing
    @SneakyThrows
    private String toJson(Object toJson) {
        return new ObjectMapper().writeValueAsString(toJson);
    }
}
