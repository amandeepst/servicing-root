package com.worldpay.servicing.model.dto.ormb;

import java.util.List;

public class SettlementGroupList {

  private String partyId;

  private List<SettlementGroup> settlementGroup;

  public SettlementGroupList() {

  }

  public SettlementGroupList(String partyId,
      List<SettlementGroup> settlementGroup) {
    this.partyId = partyId;
    this.settlementGroup = settlementGroup;
  }

  public String getPartyId() {
    return partyId;
  }

  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  public List<SettlementGroup> getSettlementGroup() {
    return settlementGroup;
  }

  public void setSettlementGroup(
      List<SettlementGroup> settlementGroup) {
    this.settlementGroup = settlementGroup;
  }
}
