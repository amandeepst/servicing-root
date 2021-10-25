package com.worldpay.servicing.model.dto.ormb;

import com.worldpay.servicing.model.dto.CallInfo;

public class OpenBillsDto {

  private CallInfo callInfo;

  private OpenBills openBills;

  public OpenBillsDto() {
  }

  public OpenBillsDto(OpenBills openBills) {
    this.openBills = openBills;
  }

  public CallInfo getCallInfo() {
    return callInfo;
  }

  public void setCallInfo(CallInfo callInfo) {
    this.callInfo = callInfo;
  }

  public OpenBills getOpenBills() {
    return openBills;
  }

  public void setOpenBills(OpenBills openBills) {
    this.openBills = openBills;
  }

}
