package com.worldpay.servicing.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CBE_CURRENCY")
public class Currency {

  @Id
  @Column(name = "CURRENCY_ID")
  private String currencyId;

  @Column(name = "CURRENCY_NAME")
  private String currencyName;

  @Column(name = "DECIMAL_POSITIONS")
  private Integer decimalPositions;

  public String getCurrencyId() {
    return currencyId;
  }

  public void setCurrencyId(String currencyId) {
    this.currencyId = currencyId;
  }

  public String getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public Integer getDecimalPositions() {
    return decimalPositions;
  }

  public void setDecimalPositions(Integer currencyPositions) {
    this.decimalPositions = currencyPositions;
  }

  @Override
  public String toString() {
    return "Currency{" +
        "currencyId='" + currencyId + '\'' +
        ", currencyName='" + currencyName + '\'' +
        ", currencyPositions='" + decimalPositions + '\'' +
        '}';
  }
}
