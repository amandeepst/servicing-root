package com.worldpay.servicing.model.dto.ormb;

import java.util.List;

public class Balance {

  private String partyId;

  private List<BalanceItem> balanceList;

  public String getPartyId() {
    return partyId;
  }

  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  public List<BalanceItem> getBalanceList() {
    return balanceList;
  }

  public void setBalanceList(List<BalanceItem> balanceList) {
    this.balanceList = balanceList;
  }
}
