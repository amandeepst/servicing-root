package com.worldpay.servicing.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.worldpay.servicing.model.profile.JsonProfile.ServiceItemView;

public class CaseIdentifier {

  @JsonView(ServiceItemView.class)
  private String caseId;

  public CaseIdentifier(String caseId) {
    this.caseId = caseId;
  }

  public CaseIdentifier() {
    super();
  }

  public String getCaseId() {
    return caseId;
  }

  public void setCaseId(String caseId) {
    this.caseId = caseId;
  }
}
