package com.worldpay.servicing.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CBE_ORIGINATING_BANK_ACCOUNT")
public class OriginatingBankAccount {

  @Id
  @Column(name = "id")
  @JsonIgnore
  private Long originatingBankAccountId;

  private String merchantLedgerAccountType;

  private String currencyCd;

  private String legalCounterparty;

  private String paymentChannelIdentifier;

  private String paymentType;

  private String bankAccountRef;

  private Date validFrom;

  private Date validTo;

  public Long getOriginatingBankAccountId() {
    return originatingBankAccountId;
  }

  public void setOriginatingBankAccountId(Long originatingBankAccountId) {
    this.originatingBankAccountId = originatingBankAccountId;
  }

  public String getMerchantLedgerAccountType() {
    return merchantLedgerAccountType;
  }

  public void setMerchantLedgerAccountType(String merchantLedgerAccountType) {
    this.merchantLedgerAccountType = merchantLedgerAccountType;
  }

  public String getCurrencyCd() {
    return currencyCd;
  }

  public void setCurrencyCd(String currencyCd) {
    this.currencyCd = currencyCd;
  }

  public String getLegalCounterparty() {
    return legalCounterparty;
  }

  public void setLegalCounterparty(String legalCounterparty) {
    this.legalCounterparty = legalCounterparty;
  }

  public String getPaymentChannelIdentifier() {
    return paymentChannelIdentifier;
  }

  public void setPaymentChannelIdentifier(String paymentChannelIdentifier) {
    this.paymentChannelIdentifier = paymentChannelIdentifier;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public String getBankAccountRef() {
    return bankAccountRef;
  }

  public void setBankAccountRef(String bankAccountRef) {
    this.bankAccountRef = bankAccountRef;
  }

  public Date getValidFrom() {
    return validFrom;
  }

  public void setValidFrom(Date validFrom) {
    this.validFrom = validFrom;
  }

  public Date getValidTo() {
    return validTo;
  }

  public void setValidTo(Date validTo) {
    this.validTo = validTo;
  }
}
