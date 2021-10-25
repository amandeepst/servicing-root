package com.worldpay.servicing.gateway.outbound.model.internal;

import com.worldpay.servicing.model.dto.ServiceItemDTO;

import java.math.BigDecimal;

public class ServicingApprovalItem {

  private String caseId;

  private BigDecimal amount;

  private String userName;

  private String userPosition;

  private String creditDebit;

  private String currency;

  private String service;

  private String serviceClass;

  private String serviceId;

  private String serviceItemType;

  public ServicingApprovalItem(ServiceItemDTO serviceItem, String userName, String userPosition) {
    this.caseId = serviceItem.getCaseIdentifier().getCaseId();
    this.userName = userName;
    this.userPosition = userPosition;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }

  public String getServiceClass() {
    return serviceClass;
  }

  public void setServiceClass(String serviceClass) {
    this.serviceClass = serviceClass;
  }

  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  public String getServiceItemType() {
    return serviceItemType;
  }

  public void setServiceItemType(String serviceItemType) {
    this.serviceItemType = serviceItemType;
  }

  public String getCreditDebit() {
    return creditDebit;
  }

  public void setCreditDebit(String creditDebit) {
    this.creditDebit = creditDebit;
  }

  public String getCaseId() {
    return caseId;
  }

  public void setCaseId(String caseId) {
    this.caseId = caseId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPosition() {
    return userPosition;
  }

  public void setUserPosition(String userPosition) {
    this.userPosition = userPosition;
  }

}
