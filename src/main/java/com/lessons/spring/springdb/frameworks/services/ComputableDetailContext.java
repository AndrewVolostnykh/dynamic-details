package com.lessons.spring.springdb.frameworks.services;

import com.lessons.spring.springdb.frameworks.Tests;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.math.BigDecimal;

public class ComputableDetailContext extends StandardEvaluationContext {

    public ComputableDetailContext(Object sourceEntity) {
        setVariable("source", sourceEntity);
    }

    // set variable

    public static void main(String[] args) {
        Tests.ContractClaim contractClaim = new Tests.ContractClaim();
        contractClaim.setPaymentAmount(BigDecimal.valueOf(1000));
        ComputableDetailContext context = new ComputableDetailContext(contractClaim);

        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        String value = spelExpressionParser.parseExpression("2 * #source['paymentAmount']")
                .getValue(context, String.class);

        System.err.println(value);

    }
}
