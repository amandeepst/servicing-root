package com.worldpay.servicing.model.entity;

import com.worldpay.servicing.model.entity.enums.ServiceItemStatus;
import com.worldpay.servicing.model.util.Identifiable;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CBE_ADHOC_CHG")
public class AdhocCharge implements Identifiable<String> {

  @Id
  @GenericGenerator(
      name = "adhoccharge-generator",
      strategy = "com.worldpay.servicing.model.util.StringSequenceIdentifier",
      parameters = {
          @org.hibernate.annotations.Parameter(name = "sequence_name", value = "CBE_ADHOC_CHG_SEQ")
      })
  @GeneratedValue(generator = "adhoccharge-generator", strategy = GenerationType.SEQUENCE)
  @Column(name = "ADHOC_CHG_ID")
  private String adhocChargeId;

  @Column(name = "UPLOAD_DTTM")
  private Date uploadDate;

  @Column(name = "STATUS_CD")
  @Enumerated(EnumType.STRING)
  private ServiceItemStatus status = ServiceItemStatus.NOT_APPROVED;

  @Column(name = "APPROVAL_DTTM")
  private Date approvalDate;

  @Column(name = "REJ_REASON")
  private String rejectionReason;

  private String partyId;

  private String chargeType;

  private BigDecimal quantity;

  private BigDecimal amount;

  @Column(name = "LCP")
  private String legalCounterparty;

  @Column(name = "LCP_DESCR")
  private String legalCounterpartyDescr;

  @Column(name = "CURRENCY_CD")
  private String currency;

  @Column(name = "ADHOC_BILL_FLG")
  private String immediateBillFlag;

  @Column(name = "REASON")
  private String adhocChargeReason;

  @Column(name = "CASE_ID")
  private String caseIdentifier;

  private String createdBy;

  private String approvedBy;

  @Column(name = "ILM_DT")
  private Date ilmDate;

  @Column(name = "ILM_ARCH_SW")
  private String ilmArchiveFlag;

  private String chargeTypeDescr;

  @Column(name = "MODIFIED_BY")
  private String modifiedBy;

  @Column(name = "MODIFIED_AT")
  private Date modifiedAt;

  @Column(name = "APPROVAL_ID")
  private String approvalId;

  public String getLegalCounterpartyDescr() {
    return legalCounterpartyDescr;
  }

  public void setLegalCounterpartyDescr(String legalCounterpartyDescr) {
    this.legalCounterpartyDescr = legalCounterpartyDescr;
  }

  public String getChargeTypeDescr() {
    return chargeTypeDescr;
  }

  public void setChargeTypeDescr(String chargeTypeDescr) {
    this.chargeTypeDescr = chargeTypeDescr;
  }

  public String getAdhocChargeId() {
    return adhocChargeId;
  }

  public void setAdhocChargeId(String adhocChargeId) {
    this.adhocChargeId = adhocChargeId;
  }

  public Date getUploadDate() {
    return uploadDate;
  }

  public void setUploadDate(Date uploadDate) {
    this.uploadDate = uploadDate;
  }

  public ServiceItemStatus getStatus() {
    return status;
  }

  public void setStatus(ServiceItemStatus status) {
    this.status = status;
  }

  public Date getApprovalDate() {
    return approvalDate;
  }

  public void setApprovalDate(Date approvalDate) {
    this.approvalDate = approvalDate;
  }

  public String getRejectionReason() {
    return rejectionReason;
  }

  public void setRejectionReason(String rejectionReason) {
    this.rejectionReason = rejectionReason;
  }

  public String getPartyId() {
    return partyId;
  }

  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  public String getChargeType() {
    return chargeType;
  }

  public void setChargeType(String chargeType) {
    this.chargeType = chargeType;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getLegalCounterparty() {
    return legalCounterparty;
  }

  public void setLegalCounterparty(String legalCounterparty) {
    this.legalCounterparty = legalCounterparty;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getImmediateBillFlag() {
    return immediateBillFlag;
  }

  public void setImmediateBillFlag(String immediateBillFlag) {
    this.immediateBillFlag = immediateBillFlag;
  }

  public String getAdhocChargeReason() {
    return adhocChargeReason;
  }

  public void setAdhocChargeReason(String adhocChargeReason) {
    this.adhocChargeReason = adhocChargeReason;
  }

  public String getCaseIdentifier() {
    return caseIdentifier;
  }

  public void setCaseIdentifier(String caseIdentifier) {
    this.caseIdentifier = caseIdentifier;
  }

  public Date getIlmDate() {
    return ilmDate;
  }

  public void setIlmDate(Date ilmDate) {
    this.ilmDate = ilmDate;
  }

  public String getIlmArchiveFlag() {
    return ilmArchiveFlag;
  }

  public void setIlmArchiveFlag(String ilmArchiveFlag) {
    this.ilmArchiveFlag = ilmArchiveFlag;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getApprovedBy() {
    return approvedBy;
  }

  public void setApprovedBy(String approvedBy) {
    this.approvedBy = approvedBy;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public Date getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(Date modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  public String getApprovalId() {
    return approvalId;
  }

  public void setApprovalId(String approvalId) {
    this.approvalId = approvalId;
  }

  @Override
  public String getId() {
    return this.adhocChargeId;
  }

  @Override
  public String toString() {
    return "AdhocCharge [adhocChargeId="
        + adhocChargeId
        + ", uploadDate="
        + uploadDate
        + ", status="
        + status + "]";
  }
}
