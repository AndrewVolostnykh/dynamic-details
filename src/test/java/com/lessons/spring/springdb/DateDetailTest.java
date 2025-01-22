package com.lessons.spring.springdb;

import com.lessons.spring.springdb.frameworks.details.impl.ComputableDetail;
import com.lessons.spring.springdb.frameworks.details.impl.DateDetail;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DateDetailTest extends AbstractUnitTest {

    @Test
    public void dateDetail_toString_works() {
        // given
        LocalDateTime now = LocalDateTime.now();
        DateDetail detail = new DateDetail();
        detail.setValue(now);
        // when/then
        assertEquals(now, detail.getValue());
    }

    // TODO: related detail should be surrounded with quotes.
    // should be LocalDateTime object
    @Test
    public void dateDetail_compareInComputableDetail_validResult() {
        // given
        DateDetail dateDetail = new DateDetail();
        dateDetail.setValue(LocalDateTime.now().minusDays(1L));
        ComputableDetail<Boolean> datesComparisonDetail = new ComputableDetail<>();
        datesComparisonDetail.setBusinessContext(businessContext);
        datesComparisonDetail.setRelatedDetails(Map.of(1L, dateDetail));
        datesComparisonDetail.setFormula("@date.isAfter('<1>', @date.now())");

        // when/then
        assertFalse(datesComparisonDetail.getValue());
    }
}
