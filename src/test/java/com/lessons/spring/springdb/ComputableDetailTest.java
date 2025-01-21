package com.lessons.spring.springdb;

import com.lessons.spring.springdb.frameworks.details.impl.ComputableDetail;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputableDetailTest extends AbstractUnitTest {
    // compute with source

    @Test
    public void computableDetailBigDecimal() {
        // given
        ComputableDetail<String> computableDetail = new ComputableDetail<>();
        computableDetail.setFormula("1000");
        computableDetail.setBusinessContext(businessContext);

        // when/then
        assertEquals(1000, computableDetail.getValue());
    }

    @Test
    public void computableWithRelatedBigDecimal() {
        ComputableDetail<BigDecimal> relatedBigDecimal = new ComputableDetail<>();
        relatedBigDecimal.setFormula("1000");
        ComputableDetail<BigDecimal> computableDetail = new ComputableDetail<>();
        computableDetail.setFormula("@cast.toNumber(<1> * 2)");
        computableDetail.setRelatedDetails(Map.of(1L, relatedBigDecimal));

        computableDetail.setBusinessContext(businessContext);
        relatedBigDecimal.setBusinessContext(businessContext);

        assertEquals(new BigDecimal("2000"), computableDetail.getValue());
    }

    // get value from json object from related detail and make math operation
    // no casting to BigDecimal
    @Test
    public void compute_getValueFromMap_validResult() {
        ComputableDetail<BigDecimal> relatedBigDecimal = new ComputableDetail<>();
        relatedBigDecimal.setId(1L);
        relatedBigDecimal.setFormula("{\"sum\": 1000}");
        ComputableDetail<Object> computableDetail = new ComputableDetail<>();
        computableDetail.setFormula("<1>['sum'] * 2");
        computableDetail.setRelatedDetails(Map.of(1L, relatedBigDecimal));

        computableDetail.setBusinessContext(businessContext);
        relatedBigDecimal.setBusinessContext(businessContext);

        assertEquals(2000, computableDetail.getValue());
    }

}
