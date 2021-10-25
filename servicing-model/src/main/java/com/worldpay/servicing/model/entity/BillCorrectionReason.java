package com.worldpay.servicing.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CBE_REASON")
public class BillCorrectionReason {

  @Id
  @Column(name = "REASON_CD")
  private String reasonCd;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "VALID_FROM")
  private Date validFrom;

  @Column(name = "VALID_TO")
  private Date validTo;

  public String getReasonCd() {
    return reasonCd;
  }

  public void setReasonCd(String reasonCd) {
    this.reasonCd = reasonCd;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getValidFrom() {
    return validFrom;
  }

  public void setValidFrom(Date validFrom) {
    this.validFrom = validFrom;
  }

  public Date getValidTo() {
    return validTo;
  }

  public void setValidTo(Date validTo) {
    this.validTo = validTo;
  }
}