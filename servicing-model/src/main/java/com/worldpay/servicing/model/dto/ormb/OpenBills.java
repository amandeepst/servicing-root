package com.worldpay.servicing.model.dto.ormb;

import java.util.List;

public class OpenBills {

  private List<OpenBill> billList;

  public List<OpenBill> getBillList() {
    return billList;
  }

  public void setBillList(List<OpenBill> billList) {
    this.billList = billList;
  }

  public boolean hasBills() {
    return billList != null && !billList.isEmpty();
  }

  public int countBills() {
    return (hasBills()) ? billList.size() : 0;
  }
}
