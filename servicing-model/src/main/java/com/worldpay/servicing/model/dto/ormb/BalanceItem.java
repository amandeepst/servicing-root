package com.worldpay.servicing.model.dto.ormb;

import java.util.Date;

public class BalanceItem {

  private String partyId;

  private String legalCounterparty;

  private String legalCounterpartyShortCode;

  private String legalCounterpartyDescription;

  private String currency;

  private String accountType;

  private String accountBalance;

  private String subAccountType;

  private String subAccountDescription;

  private String subAccountBalance;

  private Date uploadDate;

  public String getPartyId() {
    return partyId;
  }

  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  public String getLegalCounterparty() {
    return legalCounterparty;
  }

  public void setLegalCounterparty(String legalCounterparty) {
    this.legalCounterparty = legalCounterparty;
  }

  public String getLegalCounterpartyShortCode() {
    return legalCounterpartyShortCode;
  }

  public void setLegalCounterpartyShortCode(String legalCounterpartyShortCode) {
    this.legalCounterpartyShortCode = legalCounterpartyShortCode;
  }

  public String getLegalCounterpartyDescription() {
    return legalCounterpartyDescription;
  }

  public void setLegalCounterpartyDescription(String legalCounterpartyDescription) {
    this.legalCounterpartyDescription = legalCounterpartyDescription;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public String getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(String accountBalance) {
    this.accountBalance = accountBalance;
  }

  public String getSubAccountType() {
    return subAccountType;
  }

  public void setSubAccountType(String subAccountType) {
    this.subAccountType = subAccountType;
  }

  public String getSubAccountDescription() {
    return subAccountDescription;
  }

  public void setSubAccountDescription(String subAccountDescription) {
    this.subAccountDescription = subAccountDescription;
  }

  public String getSubAccountBalance() {
    return subAccountBalance;
  }

  public void setSubAccountBalance(String subAccountBalance) {
    this.subAccountBalance = subAccountBalance;
  }

  public Date getUploadDate() {
    return uploadDate;
  }

  public void setUploadDate(Date uploadDate) {
    this.uploadDate = uploadDate;
  }
}