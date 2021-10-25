package com.worldpay.servicing.common.context;

public class UserInfoStore {

  private String loginName;

  private String positionName;

  private String jwtName;

  private String correlationId;

  public void clear() {
    this.loginName = null;
    this.positionName = null;
    this.jwtName = null;
    this.correlationId = null;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getPositionName() {
    return positionName;
  }

  public void setPositionName(String positionName) {
    this.positionName = positionName;
  }

  public String getJwtName() {
    return jwtName;
  }

  public void setJwtName(String jwtName) {
    this.jwtName = jwtName;
  }

  public String getCorrelationId() {
    return correlationId;
  }

  public void setCorrelationId(String correlationId) {
    this.correlationId = correlationId;
  }
}
