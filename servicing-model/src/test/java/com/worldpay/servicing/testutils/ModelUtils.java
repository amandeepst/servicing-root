package com.worldpay.servicing.testutils;

import com.worldpay.servicing.model.entity.AdhocCharge;
import com.worldpay.servicing.model.entity.FinancialAdjustment;
import com.worldpay.servicing.model.entity.Payment;
import com.worldpay.servicing.model.entity.enums.ServiceItemStatus;

import java.math.BigDecimal;
import java.util.Date;

public class ModelUtils {

  private ModelUtils() {
    // empty private constructor
  }

  public static Payment createSimplePayment(String paymentId) {
    Payment payment = new Payment();
    payment.setPaymentId(paymentId);
    return payment;
  }

  public static class FinancialAdjustmentBuilder {

    private String adjustmentId;

    private String adjustmentClass;

    private String adjustmentCode;

    private String adjustmentType;

    private String adjustmentReason;

    private String adjustmentCause;

    private String fromPartyId;

    private String fromCurrency;

    private String fromSubAccount;

    private String fromLegalCounterparty;

    private String toPartyId;

    private String toCurrency;

    private String toSubAccount;

    private String toLegalCounterparty;

    private String immediateBillFlag;

    private String fastestRouteFlag;

    private String paymentNarrative;

    private String releaseReserveFlag;

    private String releaseWafFlag;

    private String caseIdentifier;

    private String createdBy;

    private String approvedBy;

    private String ilmArchiveFlag;

    private ServiceItemStatus status;

    private Date approvalDate;

    private Date uploadDate;

    private Date debtDate;

    private Date ilmDate;

    private BigDecimal fromAmount;

    private BigDecimal toAmount;

    private String adjustmentTypeDescr;

    private String adjustmentReasonDescr;

    private String adjustmentCauseDescr;

    public static FinancialAdjustmentBuilder newAdjustment() {
      return new FinancialAdjustmentBuilder();
    }

    public FinancialAdjustmentBuilder adjustmentId(String adjustmentId) {
      this.adjustmentId = adjustmentId;
      return this;
    }

    public FinancialAdjustmentBuilder adjustmentClass(String adjustmentClass) {
      this.adjustmentClass = adjustmentClass;
      return this;
    }

    public FinancialAdjustmentBuilder adjustmentCode(String adjustmentCode) {
      this.adjustmentCode = adjustmentCode;
      return this;
    }

    public FinancialAdjustmentBuilder adjustmentCauseDescr(String adjustmentCauseDescr) {
      this.adjustmentCauseDescr = adjustmentCauseDescr;
      return this;
    }

    public FinancialAdjustmentBuilder adjustmentReasonDescr(String adjustmentReasonDescr) {
      this.adjustmentReasonDescr = adjustmentReasonDescr;
      return this;
    }

    public FinancialAdjustmentBuilder adjustmentTypeDescr(String adjustmentTypeDescr) {
      this.adjustmentTypeDescr = adjustmentTypeDescr;
      return this;
    }

    public FinancialAdjustmentBuilder adjustmentType(String adjustmentType) {
      this.adjustmentType = adjustmentType;
      return this;
    }

    public FinancialAdjustmentBuilder adjustmentReason(String adjustmentReason) {
      this.adjustmentReason = adjustmentReason;
      return this;
    }

    public FinancialAdjustmentBuilder adjustmentCause(String adjustmentCause) {
      this.adjustmentCause = adjustmentCause;
      return this;
    }

    public FinancialAdjustmentBuilder fromPartyId(String fromPartyId) {
      this.fromPartyId = fromPartyId;
      return this;
    }

    public FinancialAdjustmentBuilder fromCurrency(String fromCurrency) {
      this.fromCurrency = fromCurrency;
      return this;
    }

    public FinancialAdjustmentBuilder fromSubAccount(String fromSubAccount) {
      this.fromSubAccount = fromSubAccount;
      return this;
    }

    public FinancialAdjustmentBuilder fromLegalCounterparty(String fromLegalCounterparty) {
      this.fromLegalCounterparty = fromLegalCounterparty;
      return this;
    }

    public FinancialAdjustmentBuilder toPartyId(String toPartyId) {
      this.toPartyId = toPartyId;
      return this;
    }

    public FinancialAdjustmentBuilder toCurrency(String toCurrency) {
      this.toCurrency = toCurrency;
      return this;
    }

    public FinancialAdjustmentBuilder toSubAccount(String toSubAccount) {
      this.toSubAccount = toSubAccount;
      return this;
    }

    public FinancialAdjustmentBuilder toLegalCounterparty(String toLegalCounterparty) {
      this.toLegalCounterparty = toLegalCounterparty;
      return this;
    }

    public FinancialAdjustmentBuilder immediateBillFlag(String immediateBillFlag) {
      this.immediateBillFlag = immediateBillFlag;
      return this;
    }

    public FinancialAdjustmentBuilder fastestRouteFlag(String fastestRouteFlag) {
      this.fastestRouteFlag = fastestRouteFlag;
      return this;
    }

    public FinancialAdjustmentBuilder paymentNarrative(String paymentNarrative) {
      this.paymentNarrative = paymentNarrative;
      return this;
    }

    public FinancialAdjustmentBuilder releaseReserveFlag(String releaseReserveFlag) {
      this.releaseReserveFlag = releaseReserveFlag;
      return this;
    }

    public FinancialAdjustmentBuilder releaseWafFlag(String releaseWafFlag) {
      this.releaseWafFlag = releaseWafFlag;
      return this;
    }

    public FinancialAdjustmentBuilder caseIdentifier(String caseIdentifier) {
      this.caseIdentifier = caseIdentifier;
      return this;
    }

    public FinancialAdjustmentBuilder createdBy(String createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public FinancialAdjustmentBuilder approvedBy(String approvedBy) {
      this.approvedBy = approvedBy;
      return this;
    }

    public FinancialAdjustmentBuilder ilmArchiveFlag(String ilmArchiveFlag) {
      this.ilmArchiveFlag = ilmArchiveFlag;
      return this;
    }

    public FinancialAdjustmentBuilder status(ServiceItemStatus status) {
      this.status = status;
      return this;
    }

    public FinancialAdjustmentBuilder approvalDate(Date approvalDate) {
      this.approvalDate = approvalDate;
      return this;
    }

    public FinancialAdjustmentBuilder uploadDate(Date uploadDate) {
      this.uploadDate = uploadDate;
      return this;
    }

    public FinancialAdjustmentBuilder debtDate(Date debtDate) {
      this.debtDate = debtDate;
      return this;
    }

    public FinancialAdjustmentBuilder ilmDate(Date ilmDate) {
      this.ilmDate = ilmDate;
      return this;
    }

    public FinancialAdjustmentBuilder fromAmount(BigDecimal fromAmount) {
      this.fromAmount = fromAmount;
      return this;
    }

    public FinancialAdjustmentBuilder toAmount(BigDecimal toAmount) {
      this.toAmount = toAmount;
      return this;
    }

    public FinancialAdjustment build() {
      FinancialAdjustment fa = new FinancialAdjustment();
      fa.setAdjustmentId(adjustmentId);
      fa.setApprovalDate(approvalDate);
      fa.setApprovedBy(approvedBy);
      fa.setCaseIdentifier(caseIdentifier);
      fa.setCreatedBy(createdBy);
      fa.setIlmArchiveFlag(ilmArchiveFlag);
      fa.setIlmDate(ilmDate);
      fa.setAdjustmentCauseDescr(adjustmentCauseDescr);
      fa.setAdjustmentReasonDescr(adjustmentReasonDescr);
      fa.setAdjustmentTypeDescr(adjustmentTypeDescr);
      fa.setUploadDate(uploadDate);
      fa.setAdjustmentCause(adjustmentCause);
      fa.setAdjustmentClass(adjustmentClass);
      fa.setAdjustmentCode(adjustmentCode);
      fa.setAdjustmentId(adjustmentId);
      fa.setAdjustmentReason(adjustmentReason);
      fa.setAdjustmentType(adjustmentType);
      fa.setDebtDate(debtDate);
      fa.setFastestRouteFlag(fastestRouteFlag);
      fa.setFromAmount(fromAmount);
      fa.setFromCurrency(fromCurrency);
      fa.setFromLegalCounterparty(fromLegalCounterparty);
      fa.setFromPartyId(fromPartyId);
      fa.setFromSubAccount(fromSubAccount);
      fa.setImmediateBillFlag(immediateBillFlag);
      fa.setPaymentNarrative(paymentNarrative);
      fa.setReleaseReserveFlag(releaseReserveFlag);
      fa.setReleaseWafFlag(releaseWafFlag);
      fa.setStatus(status);
      fa.setToAmount(toAmount);
      fa.setToCurrency(toCurrency);
      fa.setToLegalCounterparty(toLegalCounterparty);
      fa.setToPartyId(toPartyId);
      fa.setToSubAccount(toSubAccount);
      fa.setUploadDate(uploadDate);
      return fa;
    }
  }

  public static class PaymentBuilder {

    private Date uploadDate;

    private ServiceItemStatus paymentStatus;

    private Date approvalDate;

    private Date dueDate;

    private Date billDate;

    private Date ilmDate;

    private String paymentId;

    private String paymentClass;

    private String paymentReference;

    private String partyIdentifier;

    private String paymentChannel;

    private String currency;

    private String lineId;

    private String accountType;

    private String billId;

    private String alternateBillId;

    private String legalCounterparty;

    private String bankingEntryStatus;

    private String bankAcctRef;

    private String caseIdentifier;

    private String createdBy;

    private String approvedBy;

    private String ilmArchiveFlag;

    private BigDecimal totalPaymentAmount;

    private BigDecimal lineAmount;

    private BigDecimal paidAmount;

    private BigDecimal unpaidAmount;

    private BigDecimal latestBankingEntryEvent;

    private String parentPaymentId;

    private FinancialAdjustment financialAdjustment;

    private String paymentType;

    private String legalCounterpartyDescr;

    public static PaymentBuilder newPayment() {
      return new PaymentBuilder();
    }

    public PaymentBuilder paymentClass(String paymentClass) {
      this.paymentClass = paymentClass;
      return this;
    }

    public PaymentBuilder paymentType(String paymentType) {
      this.paymentType = paymentType;
      return this;
    }

    public PaymentBuilder paymentReference(String paymentReference) {
      this.paymentReference = paymentReference;
      return this;
    }

    public PaymentBuilder partyIdentifier(String partyIdentifier) {
      this.partyIdentifier = partyIdentifier;
      return this;
    }

    public PaymentBuilder paymentChannel(String paymentChannel) {
      this.paymentChannel = paymentChannel;
      return this;
    }

    public PaymentBuilder currency(String currency) {
      this.currency = currency;
      return this;
    }

    public PaymentBuilder lineId(String lineId) {
      this.lineId = lineId;
      return this;
    }

    public PaymentBuilder accountType(String accountType) {
      this.accountType = accountType;
      return this;
    }

    public PaymentBuilder billId(String billId) {
      this.billId = billId;
      return this;
    }

    public PaymentBuilder alternateBillId(String alternateBillId) {
      this.alternateBillId = alternateBillId;
      return this;
    }

    public PaymentBuilder legalCounterparty(String legalCounterparty) {
      this.legalCounterparty = legalCounterparty;
      return this;
    }

    public PaymentBuilder bankingEntryStatus(String bankingEntryStatus) {
      this.bankingEntryStatus = bankingEntryStatus;
      return this;
    }

    public PaymentBuilder bankAcctRef(String bankAcctRef) {
      this.bankAcctRef = bankAcctRef;
      return this;
    }

    public PaymentBuilder caseIdentifier(String caseIdentifier) {
      this.caseIdentifier = caseIdentifier;
      return this;
    }

    public PaymentBuilder createdBy(String createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public PaymentBuilder approvedBy(String approvedBy) {
      this.approvedBy = approvedBy;
      return this;
    }

    public PaymentBuilder ilmArchiveFlag(String ilmArchiveFlag) {
      this.ilmArchiveFlag = ilmArchiveFlag;
      return this;
    }

    // -------------------------
    public PaymentBuilder totalPaymentAmount(BigDecimal totalPaymentAmount) {
      this.totalPaymentAmount = totalPaymentAmount;
      return this;
    }

    public PaymentBuilder paidAmount(BigDecimal paidAmount) {
      this.paidAmount = paidAmount;
      return this;
    }

    public PaymentBuilder lineAmount(BigDecimal lineAmount) {
      this.lineAmount = lineAmount;
      return this;
    }

    public PaymentBuilder unpaidAmount(BigDecimal unpaidAmount) {
      this.unpaidAmount = unpaidAmount;
      return this;
    }

    public PaymentBuilder latestBankingEntryEvent(BigDecimal latestBankingEntryEvent) {
      this.latestBankingEntryEvent = latestBankingEntryEvent;
      return this;
    }

    public PaymentBuilder parentPaymentId(String parentPaymentId) {
      this.parentPaymentId = parentPaymentId;
      return this;
    }

    public PaymentBuilder financialAdjustment(FinancialAdjustment financialAdjustment) {
      this.financialAdjustment = financialAdjustment;
      return this;
    }


    public PaymentBuilder paymentId(String paymentId) {
      this.paymentId = paymentId;
      return this;
    }

    public PaymentBuilder uploadDate(Date uploadDate) {
      this.uploadDate = uploadDate;
      return this;
    }

    public PaymentBuilder paymentStatus(ServiceItemStatus paymentStatus) {
      this.paymentStatus = paymentStatus;
      return this;
    }

    public PaymentBuilder approvalDate(Date approvalDate) {
      this.approvalDate = approvalDate;
      return this;
    }

    public PaymentBuilder dueDate(Date dueDate) {
      this.dueDate = dueDate;
      return this;
    }

    public PaymentBuilder billDate(Date billDate) {
      this.billDate = billDate;
      return this;
    }

    public PaymentBuilder ilmDate(Date ilmDate) {
      this.ilmDate = ilmDate;
      return this;
    }

    public PaymentBuilder legalCounterpartyDescr(String legalCounterpartyDescr) {
      this.legalCounterpartyDescr = legalCounterpartyDescr;
      return this;
    }

    public Payment build() {
      Payment p = new Payment();
      p.setAlternateBillId(alternateBillId);
      p.setApprovalDate(approvalDate);
      p.setApprovedBy(approvedBy);
      p.setBankAcctRef(bankAcctRef);
      p.setBankingEntryStatus(bankingEntryStatus);
      p.setBillId(billId);
      p.setPaymentType(paymentType);
      p.setCaseIdentifier(caseIdentifier);
      p.setCreatedBy(createdBy);
      p.setCurrency(currency);
      p.setDueDate(dueDate);
      p.setBillDate(billDate);
      p.setFinancialAdjustment(financialAdjustment);
      p.setIlmArchiveFlag(ilmArchiveFlag);
      p.setIlmDate(ilmDate);
      p.setLatestBankingEntryEvent(latestBankingEntryEvent);
      p.setLegalCounterparty(legalCounterparty);
      p.setLineId(lineId);
      p.setPaidAmount(paidAmount);
      p.setLineAmount(lineAmount);
      p.setUnpaidAmount(unpaidAmount);
      p.setParentPaymentId(parentPaymentId);
      p.setPartyIdentifier(partyIdentifier);
      p.setPaymentChannel(paymentChannel);
      p.setPaymentClass(paymentClass);
      p.setPaymentId(paymentId);
      p.setPaymentReference(paymentReference);
      p.setPaymentStatus(paymentStatus);
      p.setAccountType(accountType);
      p.setTotalPaymentAmount(totalPaymentAmount);
      p.setUploadDate(uploadDate);
      p.setLegalCounterpartyDescr(legalCounterpartyDescr);
      return p;
    }
  }

  public static class AdhocChargeBuilder {

    private String adhocChargeId;

    private Date uploadDate;

    private ServiceItemStatus status;

    private Date approvalDate;

    private String partyId;

    private String chargeType;

    private BigDecimal quantity;

    private BigDecimal amount;

    private String legalCounterparty;

    private String currency;

    private String immediateBillFlag;

    private String adhocChargeReason;

    private String caseIdentifier;

    private String createdBy;

    private String approvedBy;

    private Date ilmDate;

    private String ilmArchiveFlag;

    private String chargeTypeDescr;

    private String legalCounterpartyDescr;

    public static AdhocChargeBuilder newAdhocCharge() {
      return new AdhocChargeBuilder();
    }

    public AdhocChargeBuilder ilmDate(Date ilmDate) {
      this.ilmDate = ilmDate;
      return this;
    }

    public AdhocChargeBuilder createdBy(String createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public AdhocChargeBuilder approvedBy(String approvedBy) {
      this.approvedBy = approvedBy;
      return this;
    }

    public AdhocChargeBuilder ilmArchiveFlag(String ilmArchiveFlag) {
      this.ilmArchiveFlag = ilmArchiveFlag;
      return this;
    }

    public AdhocChargeBuilder immediateBillFlag(String immediateBillFlag) {
      this.immediateBillFlag = immediateBillFlag;
      return this;
    }

    public AdhocChargeBuilder adhocChargeReason(String adhocChargeReason) {
      this.adhocChargeReason = adhocChargeReason;
      return this;
    }

    public AdhocChargeBuilder caseIdentifier(String caseIdentifier) {
      this.caseIdentifier = caseIdentifier;
      return this;
    }

    public AdhocChargeBuilder legalCounterparty(String legalCounterparty) {
      this.legalCounterparty = legalCounterparty;
      return this;
    }

    public AdhocChargeBuilder currency(String currency) {
      this.currency = currency;
      return this;
    }

    public AdhocChargeBuilder adhocChargeId(String adhocChargeId) {
      this.adhocChargeId = adhocChargeId;
      return this;
    }

    public AdhocChargeBuilder partyId(String partyId) {
      this.partyId = partyId;
      return this;
    }

    public AdhocChargeBuilder quantity(BigDecimal quantity) {
      this.quantity = quantity;
      return this;
    }

    public AdhocChargeBuilder amount(BigDecimal amount) {
      this.amount = amount;
      return this;
    }

    public AdhocChargeBuilder chargeType(String chargeType) {
      this.chargeType = chargeType;
      return this;
    }

    public AdhocChargeBuilder uploadDate(Date uploadDate) {
      this.uploadDate = uploadDate;
      return this;
    }

    public AdhocChargeBuilder approvalDate(Date approvalDate) {
      this.approvalDate = approvalDate;
      return this;
    }

    public AdhocChargeBuilder status(ServiceItemStatus status) {
      this.status = status;
      return this;
    }

    public AdhocChargeBuilder chargeTypeDescr(String chargeTypeDescr) {
      this.chargeTypeDescr = chargeTypeDescr;
      return this;
    }

    public AdhocChargeBuilder legalCounterpartyDescr(String legalCounterpartyDescr) {
      this.legalCounterpartyDescr = legalCounterpartyDescr;
      return this;
    }

    public AdhocCharge build() {
      AdhocCharge a = new AdhocCharge();
      a.setAdhocChargeId(adhocChargeId);
      a.setAmount(amount);
      a.setAdhocChargeReason(adhocChargeReason);
      a.setApprovalDate(approvalDate);
      a.setApprovedBy(approvedBy);
      a.setCaseIdentifier(caseIdentifier);
      a.setChargeType(chargeType);
      a.setChargeTypeDescr(chargeTypeDescr);
      a.setCreatedBy(createdBy);
      a.setCurrency(currency);
      a.setIlmArchiveFlag(ilmArchiveFlag);
      a.setIlmDate(ilmDate);
      a.setImmediateBillFlag(immediateBillFlag);
      a.setLegalCounterparty(legalCounterparty);
      a.setPartyId(partyId);
      a.setQuantity(quantity);
      a.setStatus(status);
      a.setLegalCounterpartyDescr(legalCounterpartyDescr);
      a.setUploadDate(uploadDate);

      return a;
    }
  }
}
