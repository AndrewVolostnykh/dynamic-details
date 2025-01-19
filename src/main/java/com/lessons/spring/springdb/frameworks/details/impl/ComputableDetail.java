package com.lessons.spring.springdb.frameworks.details.impl;

import com.lessons.spring.springdb.frameworks.DetailType;
import com.lessons.spring.springdb.frameworks.details.DecimalDetail;
import com.lessons.spring.springdb.frameworks.services.ComputableDetailContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComputableDetail implements DecimalDetail {

    public static final String ALIAS_REPLACING_PATTERN = "<\\w+>";
    private static final Pattern PATTERN = Pattern.compile(ALIAS_REPLACING_PATTERN);

    private Long id;
    private String formula;
    private String code;
    private String name;
    private Map<Long, DecimalDetail> relatedDetails = new HashMap<>();

    private transient List<String> stackLog = new ArrayList<>();

    public ComputableDetail(Long id, String formula, Map<Long, DecimalDetail> relatedDetails) {
        this.formula = formula;
        this.relatedDetails = relatedDetails;
        this.id = id;
    }

    public BigDecimal getValue() {
        stackLog.add(String.format("Computing %s with formula %s", id, formula));
        ComputableDetailContext context = new ComputableDetailContext(this);
        SpelExpressionParser parser = new SpelExpressionParser();
        String preparedFormula = prepareFormulaToExecution();
        stackLog.add(String.format("Prepared formula [%s]", preparedFormula));
        String rawValue = parser.parseExpression(preparedFormula).getValue(context, String.class);
        if (rawValue != null) {
            stackLog.add("Result value: " + rawValue);
            return new BigDecimal(rawValue);
        }

        return BigDecimal.ZERO;
    }

    private BigDecimal getDetailValue(DecimalDetail detail) {
        if (detail == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal value = detail.getValue();
        if (detail instanceof ComputableDetail) {
            stackLog.add(String.format("Sub-detail id=[%s] stack log", detail.getId()));
            stackLog.addAll(((ComputableDetail) detail).getStackLog());
        }

        return value;
    }

    public String prepareFormulaToExecution() {
        Map<String, BigDecimal> result = new HashMap<>();
        Matcher matcher = PATTERN.matcher(formula);
        while (matcher.find()) {
            Long relatedDetailId = Long.parseLong(matcher.group().replaceAll("[\\<\\>]", ""));
            DecimalDetail detail = relatedDetails.get(relatedDetailId);
            String group = matcher.group();
            BigDecimal value = getDetailValue(detail);
            stackLog.add(String.format("Detected detail sub-value: id=[%s], value=[%s]", group, value));
            result.put(
                    matcher.group(),
                    value
            );

        }

        for (Map.Entry<String, BigDecimal> entry : result.entrySet()) {
            formula = formula.replace(
                    entry.getKey(),
                    entry.getValue() == null ? BigDecimal.ZERO.toString() : entry.getValue().toString()
            );
        }

        return formula;
    }

    @Override
    public DetailType getType() {
        return DetailType.COMPUTABLE;
    }
}
