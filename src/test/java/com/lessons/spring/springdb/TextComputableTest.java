package com.lessons.spring.springdb;

import com.lessons.spring.springdb.frameworks.details.impl.ComputableDetail;
import com.lessons.spring.springdb.frameworks.details.impl.TextDetail;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextComputableTest extends AbstractUnitTest {

    @Test
    public void computableDetail_concatSeveralTextDetails_correctResult() {
        TextDetail userPhoneNumber = new TextDetail();
        userPhoneNumber.setValue("1234567890");
        TextDetail userDetails = new TextDetail();
        userDetails.setValue("Test User");
        ComputableDetail<String> computableDetail = new ComputableDetail<>();
        computableDetail.setBusinessContext(businessContext);
        computableDetail.setFormula("'Contact to <1>; phone number: <2>'");
        computableDetail.setRelatedDetails(
                Map.of(
                        1L, userDetails,
                        2L, userPhoneNumber

                )
        );

        assertEquals(
                "Contact to Test User; phone number: 1234567890",
                computableDetail.getValue()
        );
    }

}
