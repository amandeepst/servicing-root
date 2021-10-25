package com.worldpay.servicing.model.dto.ormb;

import com.worldpay.servicing.model.dto.CallInfo;

public class MerchantBalanceDTO {

  private CallInfo callInfo;

  private Balance balance;

  public CallInfo getCallInfo() {
    return callInfo;
  }

  public void setCallInfo(CallInfo callInfo) {
    this.callInfo = callInfo;
  }

  public Balance getBalance() {
    return balance;
  }

  public void setBalance(Balance balance) {
    this.balance = balance;
  }
}
