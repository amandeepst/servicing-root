package com.worldpay.servicing.gateway.outbound.model;

import java.util.List;

public class PartyChildren {

  private String partyId;

  private List<String> children;

  public String getPartyId() {
    return partyId;
  }

  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  public List<String> getChildren() {
    return children;
  }

  public void setChildren(List<String> children) {
    this.children = children;
  }

  @Override
  public String toString() {
    return "PartyChildren{" +
        "partyId='" + partyId + '\'' +
        ", children=" + children +
        '}';
  }
}
