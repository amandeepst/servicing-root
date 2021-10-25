package com.worldpay.servicing.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.worldpay.servicing.model.profile.JsonProfile.ServiceItemView;

public class CallInfo {

  @JsonView(ServiceItemView.class)
  private String callId;

  @JsonView(ServiceItemView.class)
  private String callStatus;

  @JsonView(ServiceItemView.class)
  private String callType;

  @JsonView(ServiceItemView.class)
  private String callDescription;

  @JsonView(ServiceItemView.class)
  private String creationDate;

  public String getCallId() {
    return callId;
  }

  public void setCallId(String callId) {
    this.callId = callId;
  }

  public String getCallStatus() {
    return callStatus;
  }

  public void setCallStatus(String callStatus) {
    this.callStatus = callStatus;
  }

  public String getCallType() {
    return callType;
  }

  public void setCallType(String callType) {
    this.callType = callType;
  }

  public String getCallDescription() {
    return callDescription;
  }

  public void setCallDescription(String callDescription) {
    this.callDescription = callDescription;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }
}
