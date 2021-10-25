package com.worldpay.servicing.model.dto.ormb;

import java.math.BigDecimal;
import java.util.Date;

public class Price {

  private Long id;

  private String priceId;

  private String chargeType;

  private String chargeTypeDescr;

  private Date endDate;

  private String partyId;

  private BigDecimal perItemRate;

  private BigDecimal percentRate;

  private String priceConfig;

  private BigDecimal rank;

  private Date startDate;

  private String currencycd;

  public String getCurrencycd() {
    return currencycd;
  }

  public void setCurrencycd(String currency) {
    this.currencycd = currency;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPriceId() {
    return priceId;
  }

  public void setPriceId(String priceId) {
    this.priceId = priceId;
  }

  public String getChargeType() {
    return chargeType;
  }

  public void setChargeType(String chargeType) {
    this.chargeType = chargeType;
  }

  public String getChargeTypeDescr() {
    return chargeTypeDescr;
  }

  public void setChargeTypeDescr(String chargeTypeDescr) {
    this.chargeTypeDescr = chargeTypeDescr;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getPartyId() {
    return partyId;
  }

  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  public BigDecimal getPerItemRate() {
    return perItemRate;
  }

  public void setPerItemRate(BigDecimal perItemRate) {
    this.perItemRate = perItemRate;
  }

  public BigDecimal getPercentRate() {
    return percentRate;
  }

  public void setPercentRate(BigDecimal percentRate) {
    this.percentRate = percentRate;
  }

  public String getPriceConfig() {
    return priceConfig;
  }

  public void setPriceConfig(String priceConfig) {
    this.priceConfig = priceConfig;
  }

  public BigDecimal getRank() {
    return rank;
  }

  public void setRank(BigDecimal rank) {
    this.rank = rank;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
}
