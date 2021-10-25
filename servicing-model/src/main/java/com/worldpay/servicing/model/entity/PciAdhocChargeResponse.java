package com.worldpay.servicing.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.worldpay.servicing.model.entity.enums.ResponseStatus;
import com.worldpay.servicing.model.profile.JsonProfile.GETPciAdhocChargesView;
import com.worldpay.servicing.model.profile.JsonProfile.POSTPciAdhocChargesView;

import java.util.ArrayList;
import java.util.List;

public class PciAdhocChargeResponse {

  @JsonView(GETPciAdhocChargesView.class)
  private ResponseStatus status;

  @JsonView(POSTPciAdhocChargesView.class)
  private String adhocChargeId;

  @JsonView(GETPciAdhocChargesView.class)
  private List<String> errors;

  public PciAdhocChargeResponse() {
  }

  public PciAdhocChargeResponse(ResponseStatus status) {
    this.status = status;
  }

  public ResponseStatus getStatus() {
    return status;
  }

  public void setStatus(ResponseStatus status) {
    this.status = status;
  }

  public String getAdhocChargeId() {
    return adhocChargeId;
  }

  public void setAdhocChargeId(String adhocChargeId) {
    this.adhocChargeId = adhocChargeId;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }

  public void addError(String error) {
    if (errors == null) {
      errors = new ArrayList<>();
    }
    errors.add(error);
  }

  @Override
  public String toString() {
    return "PciAdhocChargeResponse{" +
        "status=" + status +
        ", adhocChargeId='" + adhocChargeId + '\'' +
        ", errors=" + errors +
        '}';
  }
}
