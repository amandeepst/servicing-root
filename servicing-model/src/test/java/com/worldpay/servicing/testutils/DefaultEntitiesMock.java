package com.worldpay.servicing.testutils;

import com.worldpay.servicing.model.entity.AdhocCharge;
import com.worldpay.servicing.model.entity.FinancialAdjustment;
import com.worldpay.servicing.model.entity.Payment;
import com.worldpay.servicing.model.entity.enums.ServiceItemStatus;

import java.math.BigDecimal;
import java.util.Date;

public class DefaultEntitiesMock {

  public static Payment populatePayment(String parentPaymentId, FinancialAdjustment adjustment) {
    Payment p = ModelUtils.PaymentBuilder.newPayment()
        .uploadDate(new Date())
        .paymentStatus(ServiceItemStatus.APPROVED)
        .approvalDate(new Date())
        .paymentClass("CL1")
        .totalPaymentAmount(new BigDecimal("123456.61"))
        .currency("GBP")
        .latestBankingEntryEvent(new BigDecimal("123456"))
        .lineId("LINE1111")
        .accountType("ACC1")
        .billId("bill11")
        .alternateBillId("altbill11")
        .legalCounterparty("LCP1")
        .legalCounterpartyDescr("LCP1Descr")
        .bankingEntryStatus("A1")
        .caseIdentifier("CASE1")
        .createdBy("User2")
        .approvedBy("User1")
        .ilmDate(new Date())
        .ilmArchiveFlag("Y")
        .dueDate(new Date())
        .billDate(new Date())
        .paidAmount(new BigDecimal("123456.41"))
        .partyIdentifier("PT1")
        .paymentChannel("CH001")
        .paymentType("Type1")
        .paymentReference("REF1")
        .lineAmount(new BigDecimal("123"))
        .unpaidAmount(new BigDecimal("123"))
        .build();

    if (parentPaymentId != null) {
      p.setParentPaymentId(parentPaymentId);
    }
    if (adjustment != null) {
      p.setFinancialAdjustment(adjustment);
    }

    return p;
  }


  public static FinancialAdjustment populateAdjustment() {
    return ModelUtils.FinancialAdjustmentBuilder.newAdjustment()
        .adjustmentCause("Cause1")
        .adjustmentClass("CL1")
        .adjustmentCode("CD")
        .adjustmentId("ADJ1")
        .adjustmentReason("Reason 1")
        .adjustmentType("T1")
        .approvalDate(new Date())
        .approvedBy("User1")
        .caseIdentifier("CASE1")
        .createdBy("User2")
        .debtDate(new Date())
        .fastestRouteFlag("Y")
        .fromAmount(new BigDecimal("12345"))
        .fromCurrency("GBP")
        .fromLegalCounterparty("LCP1")
        .fromPartyId("PT1")
        .fromSubAccount("FACC")
        .ilmArchiveFlag("Y")
        .ilmDate(new Date())
        .immediateBillFlag("Y")
        .paymentNarrative("Some story here")
        .releaseReserveFlag("N")
        .releaseWafFlag("Y")
        .status(ServiceItemStatus.COMPLETED)
        .toAmount(new BigDecimal("14567"))
        .toCurrency("USD")
        .toLegalCounterparty("LCP2")
        .toPartyId("PT2")
        .adjustmentTypeDescr("ADJDesc1")
        .adjustmentReasonDescr("Reason1")
        .adjustmentCauseDescr("CauseDescr1")
        .toSubAccount("TACC")
        .uploadDate(new Date())
        .build();
  }

  public static AdhocCharge populateAdhocCharge(AdhocCharge parentAdhocCharge, FinancialAdjustment adjustment,
      AdhocCharge... childAdhocCharges) {
    return ModelUtils.AdhocChargeBuilder.newAdhocCharge()
        .adhocChargeReason("Some reason here")
        .amount(new BigDecimal("1234.55"))
        .approvalDate(new Date())
        .approvedBy("User1")
        .caseIdentifier("CASE1")
        .createdBy("User2")
        .chargeType("CH1")
        .chargeTypeDescr("My Simple Product Description")
        .immediateBillFlag("Y")
        .currency("GBP")
        .ilmArchiveFlag("Y")
        .ilmDate(new Date())
        .legalCounterparty("LCP1")
        .legalCounterpartyDescr("LCP1Descr")
        .partyId("PT1")
        .quantity(new BigDecimal("5"))
        .status(ServiceItemStatus.APPROVED)
        .uploadDate(new Date())
        .build();
  }
}
