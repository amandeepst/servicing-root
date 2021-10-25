package com.worldpay.servicing.model.dto.ormb;

import com.worldpay.servicing.model.dto.CallInfo;

public class SettlementGroupDto {

  private CallInfo callInfo;

  private SettlementGroupList settlementGroupList;

  public SettlementGroupDto() {

  }

  public SettlementGroupDto(SettlementGroupList settlementGroupList) {
    this.settlementGroupList = settlementGroupList;
  }

  public CallInfo getCallInfo() {
    return callInfo;
  }

  public void setCallInfo(CallInfo callInfo) {
    this.callInfo = callInfo;
  }

  public SettlementGroupList getSettlementGroupList() {
    return settlementGroupList;
  }

  public void setSettlementGroupList(
      SettlementGroupList settlementGroupList) {
    this.settlementGroupList = settlementGroupList;
  }

  public boolean isEmpty() {
    return settlementGroupList == null || settlementGroupList.getSettlementGroup() == null || settlementGroupList.getSettlementGroup()
        .isEmpty();
  }
}
