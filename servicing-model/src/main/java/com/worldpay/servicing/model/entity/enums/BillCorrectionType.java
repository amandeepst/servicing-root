package com.worldpay.servicing.model.entity.enums;

public enum BillCorrectionType {

  CANCEL("CBL"),
  INVOICE_RECALCULATION("IRC");

  private String classId;

  BillCorrectionType(String classId) {
    this.classId = classId;
  }

  public String getClassId() {
    return classId;
  }
}
