package com.worldpay.servicing.model.dto.ormb;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Encapasulation of the parameters sent to the ORMB bill fetch service
 */
public class BillSearchCriteria {

  private String partyId;

  private String billDateTo;

  private String billDateFrom;

  private String currency;

  private BigDecimal billAmountTo;

  private BigDecimal billAmountFrom;

  private String altBillId;

  private String legalCounterparty;

  private PaymentStatus paymentStatus;

  public String getPartyId() {
    return partyId;
  }

  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  public String getBillDateTo() {
    return billDateTo;
  }

  public void setBillDateTo(String billDateTo) {
    this.billDateTo = billDateTo;
  }

  public String getBillDateFrom() {
    return billDateFrom;
  }

  public void setBillDateFrom(String billDateFrom) {
    this.billDateFrom = billDateFrom;
  }

  public PaymentStatus getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(PaymentStatus paymentStatus) {
    this.paymentStatus = paymentStatus;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public BigDecimal getBillAmountTo() {
    return billAmountTo;
  }

  public void setBillAmountTo(BigDecimal billAmountTo) {
    this.billAmountTo = billAmountTo;
  }

  public BigDecimal getBillAmountFrom() {
    return billAmountFrom;
  }

  public void setBillAmountFrom(BigDecimal billAmountFrom) {
    this.billAmountFrom = billAmountFrom;
  }

  public String getAltBillId() {
    return altBillId;
  }

  public void setAltBillId(String altBillId) {
    this.altBillId = altBillId;
  }

  public String getLegalCounterparty() {
    return legalCounterparty;
  }

  public void setLegalCounterparty(String legalCounterparty) {
    this.legalCounterparty = legalCounterparty;
  }

  @Override
  public String toString() {
    return "BillSearchCriteria{" +
        "partyId='" + partyId + '\'' +
        ", billDateTo='" + billDateTo + '\'' +
        ", billDateFrom='" + billDateFrom + '\'' +
        ", currency='" + currency + '\'' +
        ", billAmountTo=" + billAmountTo +
        ", billAmountFrom=" + billAmountFrom +
        ", altBillId='" + altBillId + '\'' +
        ", legalCounterparty='" + legalCounterparty + '\'' +
        ", paymentStatus=" + paymentStatus +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BillSearchCriteria that = (BillSearchCriteria) o;
    return getPartyId().equals(that.getPartyId()) &&
        Objects.equals(getBillDateTo(), that.getBillDateTo()) &&
        Objects.equals(getBillDateFrom(), that.getBillDateFrom()) &&
        Objects.equals(getCurrency(), that.getCurrency()) &&
        Objects.equals(getBillAmountTo(), that.getBillAmountTo()) &&
        Objects.equals(getBillAmountFrom(), that.getBillAmountFrom()) &&
        Objects.equals(getAltBillId(), that.getAltBillId()) &&
        Objects.equals(getLegalCounterparty(), that.getLegalCounterparty()) &&
        getPaymentStatus() == that.getPaymentStatus();
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(getPartyId(), getBillDateTo(), getBillDateFrom(), getCurrency(), getBillAmountTo(), getBillAmountFrom(), getAltBillId(),
            getLegalCounterparty(), getPaymentStatus());
  }

  public enum PaymentStatus {
    PAID,
    UNPAID
  }

}
