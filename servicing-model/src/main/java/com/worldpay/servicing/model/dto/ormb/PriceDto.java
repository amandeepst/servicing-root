package com.worldpay.servicing.model.dto.ormb;

import com.worldpay.servicing.model.dto.CallInfo;

import java.util.List;

public class PriceDto {

  private CallInfo callInfo;

  private List<Price> priceList;

  public PriceDto() {
  }

  public PriceDto(List<Price> priceList) {
    this.priceList = priceList;
  }

  public CallInfo getCallInfo() {
    return callInfo;
  }

  public void setCallInfo(CallInfo callInfo) {
    this.callInfo = callInfo;
  }

  public List<Price> getPriceList() {
    return priceList;
  }

  public void setPriceList(List<Price> priceList) {
    this.priceList = priceList;
  }
}
