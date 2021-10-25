package com.worldpay.servicing.gateway.outbound.ormb;

public enum OrmbRequestParameter {

  PARTY_ID("perIdNbr"),
  BILL_DATE_FROM("billdateFrom"),
  BILL_DATE_TO("billdateTo"),
  BILL_PAYMENT_STATUS("billPaymentStatus"),
  CURRENCY("currency"),
  BILL_AMOUNT_FROM("billAmountFrom"),
  BILL_AMOUNT_TO("billAmountTo"),
  LCP("legalCounterparty"),
  ALT_BILL_ID("altBillId");

  private final String expectedParamName;

  OrmbRequestParameter(String expectedParamName) {
    this.expectedParamName = expectedParamName;
  }

  public String getExpectedParamName() {
    return expectedParamName;
  }
}
