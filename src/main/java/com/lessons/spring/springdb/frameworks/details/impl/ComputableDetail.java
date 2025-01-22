package com.lessons.spring.springdb.frameworks.details.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lessons.spring.springdb.frameworks.core.exceptions.DetailProcessException;
import com.lessons.spring.springdb.frameworks.details.DetailEntry;
import com.lessons.spring.springdb.frameworks.details.DetailType;
import com.lessons.spring.springdb.frameworks.services.context.BusinessContext;
import com.lessons.spring.springdb.frameworks.services.context.ComputableDetailContext;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// allows to execute any SpEL expressions
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComputableDetail<T> implements DetailEntry<Object> {

    public static final String ALIAS_REPLACING_PATTERN = "<\\w+>";
    private static final Pattern PATTERN = Pattern.compile(ALIAS_REPLACING_PATTERN);

    private Long id;
    private String formula;
    private String code;
    private String name;
    private Class<T> expectedReturnType;
    private Map<Long, DetailEntry<?>> relatedDetails = new HashMap<>();
    // TODO: should it be like DB related entity?
    private Object sourceEntity;

    // TODO: store as JSON object of a detail
    private transient List<String> stackLog = new ArrayList<>();
    @Transient
    @JsonIgnore
    @Setter
    private transient BusinessContext businessContext;

    public ComputableDetail(
            Long id,
            String formula,
            Map<Long, DetailEntry<?>> relatedDetails) {
        this.formula = formula;
        this.relatedDetails = relatedDetails;
        this.id = id;
    }

    public void printStackLog() {
        stackLog.forEach(System.out::println);
    }

    public T getValue() {
        try {
            if (businessContext == null) {
                throw new RuntimeException(String.format("BusinessContext is null for computable detail: %s", id));
            }

            stackLog.add(String.format("Computing %s with formula %s", id, formula));
            ComputableDetailContext context = new ComputableDetailContext(
                    sourceEntity,
                    businessContext.getBeanResolver()
            );
            SpelExpressionParser parser = new SpelExpressionParser();
            String preparedFormula = prepareFormulaToExecution();
            stackLog.add(String.format("Prepared formula [%s]", preparedFormula));
            T rawValue = parser.parseExpression(preparedFormula).getValue(context, expectedReturnType);

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
                ">>>> Computation failed. Detail did not return value. Formula: %s; Detail id: %s",
                this.formula,
                id
        );
        stackLog.add(message);
        throw new RuntimeException(message);
    }

    private Object getDetailValue(DetailEntry<?> detail) {
        Object value = detail.getValue();
        if (detail instanceof ComputableDetail) {
            stackLog.add(String.format("Sub-detail id=[%s] stack log", detail.getId()));
            stackLog.addAll(((ComputableDetail) detail).getStackLog());
        }

        return value;
    }

    public String prepareFormulaToExecution() {
        Map<String, Object> result = new HashMap<>();
        Matcher matcher = PATTERN.matcher(formula);
        while (matcher.find()) {
            Long relatedDetailId = Long.parseLong(matcher.group().replaceAll("[\\<\\>]", ""));
            DetailEntry<?> detail = relatedDetails.get(relatedDetailId);
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

    @Override
    public DetailType getType() {
        return DetailType.COMPUTABLE;
    }

    // TODO: correct exception processing
    @SneakyThrows
    private String toJson(Object toJson) {
        return new ObjectMapper().writeValueAsString(toJson);
    }
}
