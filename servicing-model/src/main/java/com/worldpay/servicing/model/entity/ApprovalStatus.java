package com.worldpay.servicing.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CBE_APPROVAL_STATUS")
public class ApprovalStatus {

  @Id
  @Column(name = "STATUS_CD")
  private String statusCd;

  @Column(name = "STATUS_NAME")
  private String statusName;


  public String getStatusCd() {
    return statusCd;
  }

  public void setStatusCd(String statusCd) {
    this.statusCd = statusCd;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

}
