package andrew.volostnykh.dynamic.details;

import andrew.volostnykh.dynamic.details.impl.ComputableDetail;
import andrew.volostnykh.dynamic.details.impl.ConstantDecimalDetail;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicableDetailsToBusinessEntityTest extends AbstractUnitTest {

    @Test
    public void applicableDetailsToBusinessEntity() {
        // given
        Contract contract = new Contract();
        contract.setRate(new BigDecimal("0.95"));
        ConstantDecimalDetail basicContractPrice = new ConstantDecimalDetail();
        basicContractPrice.setValue(new BigDecimal("2000"));
        ConstantDecimalDetail franchiseAmount = new ConstantDecimalDetail();
        franchiseAmount.setValue(new BigDecimal("100"));
        ComputableDetail<BigDecimal> price = new ComputableDetail<>();
        price.setBusinessContext(businessContext);
        price.setSourceEntity(contract);
        price.setRelatedDetails(Map.of(
                1L, basicContractPrice,
                2L, franchiseAmount
        ));
        price.setFormula("(<1> - <2>) * #source.rate");

        // when/then
        assertEquals(new BigDecimal("1805.00"), price.getValue());
    }


    @Data
    public static class Contract {
        private BigDecimal rate;
        private List<Detail<?>> details = new ArrayList<>();
    }
}
