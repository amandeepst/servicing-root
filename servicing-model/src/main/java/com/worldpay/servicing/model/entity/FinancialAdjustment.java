package com.worldpay.servicing.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "CBE_FIN_ADJ")
public class FinancialAdjustment implements Identifiable<String> {

  @Id
  @GenericGenerator(
      name = "adjustment-generator",
      strategy = "com.worldpay.servicing.model.util.StringSequenceIdentifier",
      parameters = {
          @org.hibernate.annotations.Parameter(name = "sequence_name", value = "CBE_FIN_ADJ_SEQ")
      })
  @GeneratedValue(generator = "adjustment-generator", strategy = GenerationType.SEQUENCE)
  @Column(name = "ADJ_ID")
  private String adjustmentId;

  @Column(name = "UPLOAD_DTTM")
  private Date uploadDate;

  @Column(name = "STATUS_CD")
  @Enumerated(EnumType.STRING)
  private ServiceItemStatus status = ServiceItemStatus.NOT_APPROVED;

  @Column(name = "APPROVAL_DTTM")
  private Date approvalDate;

  @Column(name = "REJ_REASON")
  private String rejectionReason;

  @Column(name = "ADJ_CLASS")
  private String adjustmentClass;

  @Column(name = "ADJ_CD")
  private String adjustmentCode;

  @Column(name = "ADJ_TYPE")
  private String adjustmentType;

  @Column(name = "ADJ_REASON")
  private String adjustmentReason;

  @Column(name = "ADJ_CAUSE")
  private String adjustmentCause;

  private String fromPartyId;

  @Column(name = "FROM_CURRENCY_CD")
  private String fromCurrency;

  @Column(name = "FROM_SUB_ACCT")
  private String fromSubAccount;

  @Column(name = "FROM_LCP")
  private String fromLegalCounterparty;

  @Column(name = "FROM_LCP_DESCR")
  private String fromLegalCounterpartyDescr;

  private BigDecimal fromAmount;

  private String toPartyId;

  @Column(name = "TO_CURRENCY_CD")
  private String toCurrency;

  @Column(name = "TO_SUB_ACCT")
  private String toSubAccount;

  @Column(name = "TO_LCP")
  private String toLegalCounterparty;

  @Column(name = "TO_LCP_DESCR")
  private String toLegalCounterpartyDescr;

  private BigDecimal toAmount;

  @Column(name = "ADHOC_BILL_FLG")
  private String immediateBillFlag;

  @Column(name = "FASTEST_FLG")
  private String fastestRouteFlag;

  @Column(name = "PAY_NARRATIVE")
  private String paymentNarrative;

  @Column(name = "REL_RESERVE_FLG")
  private String releaseReserveFlag;

  @Column(name = "REL_WAF_FLG")
  private String releaseWafFlag;

  @Column(name = "DEBT_DT")
  private Date debtDate;

  @Column(name = "CASE_ID")
  private String caseIdentifier;

  private String createdBy;

  private String approvedBy;

  @Column(name = "ILM_DT")
  private Date ilmDate;

  @Column(name = "ILM_ARCH_SW")
  private String ilmArchiveFlag;

  @Column(name = "ADJ_TYPE_DESCR")
  private String adjustmentTypeDescr;

  @Column(name = "ADJ_REASON_DESCR")
  private String adjustmentReasonDescr;

  @Column(name = "ADJ_CAUSE_DESCR")
  private String adjustmentCauseDescr;

  @Column(name = "MODIFIED_BY")
  private String modifiedBy;

  @Column(name = "MODIFIED_AT")
  private Date modifiedAt;

  @Column(name = "APPROVAL_ID")
  private String approvalId;

  public String getFromLegalCounterpartyDescr() {
    return fromLegalCounterpartyDescr;
  }

  public void setFromLegalCounterpartyDescr(String fromLegalCounterpartyDescr) {
    this.fromLegalCounterpartyDescr = fromLegalCounterpartyDescr;
  }

  public String getToLegalCounterpartyDescr() {
    return toLegalCounterpartyDescr;
  }

  public void setToLegalCounterpartyDescr(String toLegalCounterpartyDescr) {
    this.toLegalCounterpartyDescr = toLegalCounterpartyDescr;
  }

  public String getAdjustmentTypeDescr() {
    return adjustmentTypeDescr;
  }

  public void setAdjustmentTypeDescr(String adjustmentTypeDescr) {
    this.adjustmentTypeDescr = adjustmentTypeDescr;
  }

  public String getAdjustmentReasonDescr() {
    return adjustmentReasonDescr;
  }

  public void setAdjustmentReasonDescr(String adjustmentReasonDescr) {
    this.adjustmentReasonDescr = adjustmentReasonDescr;
  }

  public String getAdjustmentCauseDescr() {
    return adjustmentCauseDescr;
  }

  public void setAdjustmentCauseDescr(String adjustmentCauseDescr) {
    this.adjustmentCauseDescr = adjustmentCauseDescr;
  }

  public String getAdjustmentId() {
    return adjustmentId;
  }

  public void setAdjustmentId(String adjustmentId) {
    this.adjustmentId = adjustmentId;
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

  public String getAdjustmentClass() {
    return adjustmentClass;
  }

  public void setAdjustmentClass(String adjustmentClass) {
    this.adjustmentClass = adjustmentClass;
  }

  public String getAdjustmentCode() {
    return adjustmentCode;
  }

  public void setAdjustmentCode(String adjustmentCode) {
    this.adjustmentCode = adjustmentCode;
  }

  public String getAdjustmentType() {
    return adjustmentType;
  }

  public void setAdjustmentType(String adjustmentType) {
    this.adjustmentType = adjustmentType;
  }

  public String getAdjustmentReason() {
    return adjustmentReason;
  }

  public void setAdjustmentReason(String adjustmentReason) {
    this.adjustmentReason = adjustmentReason;
  }

  public String getAdjustmentCause() {
    return adjustmentCause;
  }

  public void setAdjustmentCause(String adjustmentCause) {
    this.adjustmentCause = adjustmentCause;
  }

  public String getFromPartyId() {
    return fromPartyId;
  }

  public void setFromPartyId(String fromPartyId) {
    this.fromPartyId = fromPartyId;
  }

  public String getFromCurrency() {
    return fromCurrency;
  }

  public void setFromCurrency(String fromCurrency) {
    this.fromCurrency = fromCurrency;
  }

  public String getFromSubAccount() {
    return fromSubAccount;
  }

  public void setFromSubAccount(String fromSubAccount) {
    this.fromSubAccount = fromSubAccount;
  }

  public String getFromLegalCounterparty() {
    return fromLegalCounterparty;
  }

  public void setFromLegalCounterparty(String fromLegalCounterparty) {
    this.fromLegalCounterparty = fromLegalCounterparty;
  }

  public BigDecimal getFromAmount() {
    return fromAmount;
  }

  public void setFromAmount(BigDecimal fromAmount) {
    this.fromAmount = fromAmount;
  }

  public String getToPartyId() {
    return toPartyId;
  }

  public void setToPartyId(String toPartyId) {
    this.toPartyId = toPartyId;
  }

  public String getToCurrency() {
    return toCurrency;
  }

  public void setToCurrency(String toCurrency) {
    this.toCurrency = toCurrency;
  }

  public String getToSubAccount() {
    return toSubAccount;
  }

  public void setToSubAccount(String toSubAccount) {
    this.toSubAccount = toSubAccount;
  }

  public String getToLegalCounterparty() {
    return toLegalCounterparty;
  }

  public void setToLegalCounterparty(String toLegalCounterparty) {
    this.toLegalCounterparty = toLegalCounterparty;
  }

  public BigDecimal getToAmount() {
    return toAmount;
  }

  public void setToAmount(BigDecimal toAmount) {
    this.toAmount = toAmount;
  }

  public String getImmediateBillFlag() {
    return immediateBillFlag;
  }

  public void setImmediateBillFlag(String immediateBillFlag) {
    this.immediateBillFlag = immediateBillFlag;
  }

  public String getFastestRouteFlag() {
    return fastestRouteFlag;
  }

  public void setFastestRouteFlag(String fastestRouteFlag) {
    this.fastestRouteFlag = fastestRouteFlag;
  }

  public String getPaymentNarrative() {
    return paymentNarrative;
  }

  public void setPaymentNarrative(String paymentNarrative) {
    this.paymentNarrative = paymentNarrative;
  }

  public String getReleaseReserveFlag() {
    return releaseReserveFlag;
  }

  public void setReleaseReserveFlag(String releaseReserveFlag) {
    this.releaseReserveFlag = releaseReserveFlag;
  }

  public String getReleaseWafFlag() {
    return releaseWafFlag;
  }

  public void setReleaseWafFlag(String releaseWafFlag) {
    this.releaseWafFlag = releaseWafFlag;
  }

  public Date getDebtDate() {
    return debtDate;
  }

  public void setDebtDate(Date debtDate) {
    this.debtDate = debtDate;
  }

  public String getCaseIdentifier() {
    return caseIdentifier;
  }

  public void setCaseIdentifier(String caseIdentifier) {
    this.caseIdentifier = caseIdentifier;
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
  public String toString() {
    return "FinancialAdjustment [adjustmentId="
        + adjustmentId
        + ", status="
        + status
        + ", adjustmentClass="
        + adjustmentClass
        + "]";
  }

  @Override
  public String getId() {
    return this.adjustmentId;
  }
}
