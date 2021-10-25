package com.worldpay.servicing.model.dto.ormb;

public class SettlementGroup {

  private Long id;

  private String currency;

  private String legalCounterparty;

  private String legalCounterpartyDescription;

  private String legalCounterpartyShortCode;

  private String subAccountCode;

  private String subAccountDescription;

  private String partyId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
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

  public String getLegalCounterpartyShortCode() {
    return legalCounterpartyShortCode;
  }

  public void setLegalCounterpartyShortCode(String legalCounterpartyShortCode) {
    this.legalCounterpartyShortCode = legalCounterpartyShortCode;
  }

  public String getSubAccountCode() {
    return subAccountCode;
  }

  public void setSubAccountCode(String subAccountCode) {
    this.subAccountCode = subAccountCode;
  }

  public String getSubAccountDescription() {
    return subAccountDescription;
  }

  public void setSubAccountDescription(String subAccountDescription) {
    this.subAccountDescription = subAccountDescription;
  }

  public String getPartyId() {
    return partyId;
  }

  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }
}
