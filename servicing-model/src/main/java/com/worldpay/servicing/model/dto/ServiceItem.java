package com.worldpay.servicing.model.dto;

import static org.springframework.util.CollectionUtils.isEmpty;

import com.fasterxml.jackson.annotation.JsonView;
import com.worldpay.servicing.model.profile.JsonProfile.ServiceItemView;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

public class ServiceItem {

  @JsonView(ServiceItemView.class)
  @Valid
  private List<AdhocChargeDTO> adhocCharges = new ArrayList<>();

  @JsonView(ServiceItemView.class)
  @Valid
  private List<FinancialAdjustmentDTO> financialAdjustments = new ArrayList<>();

  @JsonView(ServiceItemView.class)
  @Valid
  private List<PaymentDTO> payments = new ArrayList<>();

  @JsonView(ServiceItemView.class)
  @Valid
  private List<BillCorrectionDTO> billCorrections = new ArrayList<>();

  public List<AdhocChargeDTO> getAdhocCharges() {
    return adhocCharges;
  }

  public void setAdhocCharges(List<AdhocChargeDTO> adhocCharges) {
    this.adhocCharges = adhocCharges;
  }

  public List<FinancialAdjustmentDTO> getFinancialAdjustments() {
    return financialAdjustments;
  }

  public void setFinancialAdjustments(List<FinancialAdjustmentDTO> financialAdjustments) {
    this.financialAdjustments = financialAdjustments;
  }

  public List<PaymentDTO> getPayments() {
    return payments;
  }

  public void setPayments(List<PaymentDTO> payments) {
    this.payments = payments;
  }

  public List<BillCorrectionDTO> getBillCorrections() {
    return billCorrections;
  }

  public void setBillCorrections(List<BillCorrectionDTO> billCorrections) {
    this.billCorrections = billCorrections;
  }

  public boolean hasFinancialAdjustments() {
    return !isEmpty(getFinancialAdjustments());
  }

  public boolean hasPayments() {
    return !isEmpty(getPayments());
  }

  public boolean hasBillCorrections() {
    return !isEmpty(getBillCorrections());
  }

  public boolean hasAdhocCharges() {
    return !isEmpty(getAdhocCharges());
  }
}
