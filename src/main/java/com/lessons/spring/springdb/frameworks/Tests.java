package com.lessons.spring.springdb.frameworks;

import com.lessons.spring.springdb.frameworks.details.DetailEntry;
import com.lessons.spring.springdb.frameworks.details.impl.ComputableDetail;
import com.lessons.spring.springdb.frameworks.details.impl.ConstantDecimalDetail;
import com.lessons.spring.springdb.frameworks.details.impl.TextDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Tests {

    @Data
    public static class Contract {
        private Long id;
        private BigDecimal insuranceSum;

        private List<DetailEntry<?>> details;
    }

    @Data
    public static class ContractClaim {
        private Long id;
        private BigDecimal paymentAmount;
        private BigDecimal amortizationFactor;
        private Contract contract;

        private List<DetailEntry<?>> details = new ArrayList<>();

        public void addDetail(DetailEntry<?> detail) {
            details.add(detail);
        }
    }

    public static void main(String[] args) {
        testContractClaimWithPlainDetails();
    }

    public static void testContractClaimWithPlainDetails() {
        // Details: manager, client contact number, partner coefficient, partner reward

        ContractClaim claim = new ContractClaim();
        claim.setId(1L);
        claim.setPaymentAmount(BigDecimal.valueOf(1000L));
        TextDetail textDetail = new TextDetail();
        textDetail.setId(123L);
        textDetail.setValue("Іванов Іван Іванович");

        ConstantDecimalDetail constantDecimalDetail = new ConstantDecimalDetail();
        constantDecimalDetail.setId(456L);
        constantDecimalDetail.setValue(BigDecimal.valueOf(5000L)); // full insurance amount

        ConstantDecimalDetail taxes = new ConstantDecimalDetail();
        taxes.setId(70L);
        taxes.setValue(BigDecimal.valueOf(0.09)); // full insurance amount

        ConstantDecimalDetail refund = new ConstantDecimalDetail();
        refund.setId(700L);
        refund.setValue(BigDecimal.valueOf(200)); // full insurance amount

        ComputableDetail refundPercent = new ComputableDetail();
        refundPercent.setId(78L);
        refundPercent.setFormula("(<700> / <456>) * 100");

        ComputableDetail income = new ComputableDetail();
        income.setId(78L);
        income.setFormula("<700> - (<700> * <70>) * <78> ");

        refundPercent.getRelatedDetails().put(700L, refund);
        refundPercent.getRelatedDetails().put(456L, constantDecimalDetail);

        income.getRelatedDetails().put(700L, refund);
        income.getRelatedDetails().put(456L, constantDecimalDetail);
        income.getRelatedDetails().put(70L, taxes);
        income.getRelatedDetails().put(78L, refundPercent);

        System.err.println(income.getValue());
        System.err.println();
        income.getStackLog().forEach(System.out::println);
    }

    // some of the details has text type, but needed to compute something
    public void testContractClaimWithRelatedDetails() {

    }

    public void testContractClaimWithSourceObject() {

    }
}
