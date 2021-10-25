package com.worldpay.servicing.model.dto.ormb;

import java.math.BigDecimal;
import java.util.Date;

public class OpenBill {

  private String billid;

  private String lineId;

  private String accountDescription;

  private String accountType;

  private String alternateBillId;

  private String bankingEntryStatus;

  private BigDecimal billAmount;

  private Date billDate;

  private String currency;

  private Date dueDate;

  private String latestBankingEntryEvent;

  private String legalCounterparty;

  private String legalCounterpartyDescription;

  private BigDecimal lineAmount;

  private String partyId;

  private BigDecimal unpaidAmount;
  
  private boolean lockedBill = false;
  
  private String caseId;
  
  private BigDecimal currentPaidAmount;

  public String getBillid() {
    return billid;
  }

  public void setBillid(String billid) {
    this.billid = billid;
  }

  public String getLineId() {
    return lineId;
  }

  public void setLineId(String lineId) {
    this.lineId = lineId;
  }

  public String getAccountDescription() {
    return accountDescription;
  }

  public void setAccountDescription(String accountDescription) {
    this.accountDescription = accountDescription;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public String getAlternateBillId() {
    return alternateBillId;
  }

  public void setAlternateBillId(String alternateBillId) {
    this.alternateBillId = alternateBillId;
  }

  public String getBankingEntryStatus() {
    return bankingEntryStatus;
  }

  public void setBankingEntryStatus(String bankingEntryStatus) {
    this.bankingEntryStatus = bankingEntryStatus;
  }

  public BigDecimal getBillAmount() {
    return billAmount;
  }

  public void setBillAmount(BigDecimal billAmount) {
    this.billAmount = billAmount;
  }

  public Date getBillDate() {
    return billDate;
  }

  public void setBillDate(Date billDate) {
    this.billDate = billDate;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public String getLatestBankingEntryEvent() {
    return latestBankingEntryEvent;
  }

  public void setLatestBankingEntryEvent(String latestBankingEntryEvent) {
    this.latestBankingEntryEvent = latestBankingEntryEvent;
  }

  public String getLegalCounterparty() {
    return legalCounterparty;
  }

  public void setLegalCounterparty(String legalCounterparty) {
    this.legalCounterparty = legalCounterparty;
  }

  public String getLegalCounterpartyDescription() {
    return legalCounterpartyDescription;
  }

  public void setLegalCounterpartyDescription(String legalCounterpartyDescription) {
    this.legalCounterpartyDescription = legalCounterpartyDescription;
  }

  public BigDecimal getLineAmount() {
    return lineAmount;
  }

  public void setLineAmount(BigDecimal lineAmount) {
    this.lineAmount = lineAmount;
  }

  public String getPartyId() {
    return partyId;
  }

  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  public BigDecimal getUnpaidAmount() {
    return unpaidAmount;
  }

  public void setUnpaidAmount(BigDecimal unpaidAmount) {
    this.unpaidAmount = unpaidAmount;
  }

  public boolean isLockedBill() {
    return lockedBill;
  }

  public void setLockedBill(boolean lockedBill) {
    this.lockedBill = lockedBill;
  }

  public String getCaseId() {
    return caseId;
  }

  public void setCaseId(String caseId) {
    this.caseId = caseId;
  }

  public BigDecimal getCurrentPaidAmount() {
    return currentPaidAmount;
  }

  public void setCurrentPaidAmount(BigDecimal currentPaidAmount) {
    this.currentPaidAmount = currentPaidAmount;
  }
  
}
