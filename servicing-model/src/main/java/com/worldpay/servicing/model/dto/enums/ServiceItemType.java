package com.worldpay.servicing.model.dto.enums;

public enum ServiceItemType {
  ADHOC_CHARGE("ADHOC_CHG"),
  FINANCIAL_ADJUSTMENT("ADJUSTMENT"),
  PAYMENT("MP"),
  BILL_CORRECTION("BILL_CORRECTION");

  private String approvalService;

  ServiceItemType(final String approvalService) {
    this.approvalService = approvalService;
  }

  public String getApprovalService() {
    return approvalService;
  }
}
