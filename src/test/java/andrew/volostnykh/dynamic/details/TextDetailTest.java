package andrew.volostnykh.dynamic.details;

import andrew.volostnykh.dynamic.details.impl.ComputableDetail;
import andrew.volostnykh.dynamic.details.impl.TextDetail;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextDetailTest extends AbstractUnitTest {

    @Test
    public void computableDetail_concatSeveralTextDetails_correctResult() {
        // given
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

        // when/then
        assertEquals(
                "Contact to Test User; phone number: 1234567890",
                computableDetail.getValue()
        );
    }

}
