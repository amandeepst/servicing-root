package com.worldpay.servicing.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.worldpay.servicing.model.profile.JsonProfile.ServiceItemView;

import javax.validation.Valid;

public class ServiceItemDTO {

  @JsonView(ServiceItemView.class)
  private CallInfo callInfo;

  @JsonView(ServiceItemView.class)
  private CaseIdentifier caseIdentifier;

  @JsonView(ServiceItemView.class)
  @Valid
  private ServiceItem serviceItem;

  public ServiceItemDTO(ServiceItemDTO sourceServiceItem) {
    super();
    this.callInfo = sourceServiceItem.getCallInfo();
    this.caseIdentifier = sourceServiceItem.getCaseIdentifier();
    this.serviceItem = new ServiceItem();
  }

  public ServiceItemDTO() {
    super();
  }

  public CallInfo getCallInfo() {
    return callInfo;
  }

  public void setCallInfo(CallInfo callInfo) {
    this.callInfo = callInfo;
  }

  public CaseIdentifier getCaseIdentifier() {
    return caseIdentifier;
  }

  public void setCaseIdentifier(CaseIdentifier caseIdentifier) {
    this.caseIdentifier = caseIdentifier;
  }

  public ServiceItem getServiceItem() {
    return serviceItem;
  }

  public void setServiceItem(ServiceItem serviceItem) {
    this.serviceItem = serviceItem;
  }
}
