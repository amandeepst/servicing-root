package com.worldpay.servicing.model.entity;

import com.worldpay.servicing.model.entity.enums.BillCorrectionType;
import com.worldpay.servicing.model.entity.enums.BooleanFlag;
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
import javax.persistence.Transient;

@Entity
@Table(name = "CBE_BILL_CORR")
public class BillCorrection implements Identifiable<String> {

  @Id
  @GenericGenerator(
      name = "billcorrection-generator",
      strategy = "com.worldpay.servicing.model.util.StringSequenceIdentifier",
      parameters = {
          @org.hibernate.annotations.Parameter(name = "sequence_name", value = "CBE_BILL_CORR_SEQ")
      })
  @GeneratedValue(generator = "billcorrection-generator", strategy = GenerationType.SEQUENCE)
  @Column(name = "BILL_CORR_ID")
  private String billCorrId;

  @Column(name = "PARENT_CORR_ID")
  private String parentBillCorrId;

  @Column(name = "STATUS_CD")
  @Enumerated(EnumType.STRING)
  private ServiceItemStatus status = ServiceItemStatus.NOT_APPROVED;

  @Column(name = "CORR_TYPE")
  @Enumerated(EnumType.STRING)
  private BillCorrectionType type = BillCorrectionType.CANCEL;

  @Transient
  private String typeDescription;

  @Column(name = "REJ_REASON")
  private String rejectionReason;

  @Column(name = "REASON_CD")
  private String reason;

  private String partyId;

  private String billId;

  private String altBillId;

  private String lineId;

  private String caseId;

  @Column(name = "ACCT_TYPE")
  private String accountType;

  @Column(name = "LINE_AMT")
  private BigDecimal lineAmount;

  @Column(name = "REUSE_DUE_DT")
  @Enumerated(EnumType.STRING)
  private BooleanFlag reuseDueDate = BooleanFlag.N;

  @Column(name = "PAID_INVOICE")
  @Enumerated(EnumType.STRING)
  private BooleanFlag paidInvoice = BooleanFlag.N;

  @Column(name = "LATEST_BE_EVENT_ID")
  private BigDecimal latestBankingEntryEvent;

  @Column(name = "UPLOAD_DTTM")
  private Date uploadDate;

  @Column(name = "APPROVAL_DTTM")
  private Date approvalDate;

  private String createdBy;

  private String approvedBy;

  @Column(name = "ILM_DT")
  private Date ilmDate;

  @Column(name = "ILM_ARCH_SW")
  private String ilmArchiveFlag;

  @Column(name = "MODIFIED_BY")
  private String modifiedBy;

  @Column(name = "MODIFIED_AT")
  private Date modifiedAt;

  @Column(name = "CURRENCY_CD")
  private String currency;

  @Column(name = "UNPAID_AMT")
  private BigDecimal unpaidAmount;

  @Column(name = "APPROVAL_ID")
  private String approvalId;

  public String getBillCorrId() {
    return billCorrId;
  }

  public void setBillCorrId(String billCorrId) {
    this.billCorrId = billCorrId;
  }

  public String getParentBillCorrId() {
    return parentBillCorrId;
  }

  public void setParentBillCorrId(String parentBillCorrId) {
    this.parentBillCorrId = parentBillCorrId;
  }

  public ServiceItemStatus getStatus() {
    return status;
  }

  public void setStatus(ServiceItemStatus status) {
    this.status = status;
  }

  public BillCorrectionType getType() {
    return type;
  }

  public void setType(BillCorrectionType type) {
    this.type = type;
  }

  public String getRejectionReason() {
    return rejectionReason;
  }

  public void setRejectionReason(String rejectionReason) {
    this.rejectionReason = rejectionReason;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getPartyId() {
    return partyId;
  }

  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  public String getBillId() {
    return billId;
  }

  public void setBillId(String billId) {
    this.billId = billId;
  }

  public String getAltBillId() {
    return altBillId;
  }

  public void setAltBillId(String altBillId) {
    this.altBillId = altBillId;
  }

  public String getCaseId() {
    return caseId;
  }

  public void setCaseId(String caseId) {
    this.caseId = caseId;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public BigDecimal getLineAmount() {
    return lineAmount;
  }

  public void setLineAmount(BigDecimal lineAmount) {
    this.lineAmount = lineAmount;
  }

  public BooleanFlag getReuseDueDate() {
    return reuseDueDate;
  }

  public void setReuseDueDate(BooleanFlag reuseDueDate) {
    this.reuseDueDate = reuseDueDate;
  }

  public BooleanFlag getPaidInvoice() {
    return paidInvoice;
  }

  public void setPaidInvoice(BooleanFlag paidInvoice) {
    this.paidInvoice = paidInvoice;
  }

  public BigDecimal getLatestBankingEntryEvent() {
    return latestBankingEntryEvent;
  }

  public void setLatestBankingEntryEvent(BigDecimal latestBankingEntryEvent) {
    this.latestBankingEntryEvent = latestBankingEntryEvent;
  }

  public Date getUploadDate() {
    return uploadDate;
  }

  public void setUploadDate(Date uploadDate) {
    this.uploadDate = uploadDate;
  }

  public Date getApprovalDate() {
    return approvalDate;
  }

  public void setApprovalDate(Date approvalDate) {
    this.approvalDate = approvalDate;
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

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public BigDecimal getUnpaidAmount() {
    return unpaidAmount;
  }

  public void setUnpaidAmount(BigDecimal unpaidAmount) {
    this.unpaidAmount = unpaidAmount;
  }

  public String getTypeDescription() {
    return typeDescription;
  }

  public void setTypeDescription(String typeDescription) {
    this.typeDescription = typeDescription;
  }

  public String getLineId() {
    return lineId;
  }

  public void setLineId(String lineId) {
    this.lineId = lineId;
  }

  public String getApprovalId() {
    return approvalId;
  }

  public void setApprovalId(String approvalId) {
    this.approvalId = approvalId;
  }

  @Override
  public String getId() {
    return this.billCorrId;
  }

  @Override
  public String toString() {

    return "BillCorrection{" +
        "billCorrId='" + billCorrId + '\'' +
        ", parentBillCorrId='" + parentBillCorrId + '\'' +
        ", status=" + status +
        ", type=" + type +
        ", typeDescription=" + typeDescription +
        ", rejectionReason='" + rejectionReason + '\'' +
        ", reason='" + reason + '\'' +
        ", partyId='" + partyId + '\'' +
        ", billId='" + billId + '\'' +
        ", altBillId='" + altBillId + '\'' +
        ", lineId='" + lineId + '\'' +
        ", caseId='" + caseId + '\'' +
        ", accountType='" + accountType + '\'' +
        ", lineAmount=" + lineAmount +
        ", reuseDueDate=" + reuseDueDate +
        ", paidInvoice=" + paidInvoice +
        ", latestBankingEntryEvent=" + latestBankingEntryEvent +
        ", uploadDate=" + uploadDate +
        ", approvalDate=" + approvalDate +
        ", createdBy='" + createdBy + '\'' +
        ", approvedBy='" + approvedBy + '\'' +
        ", ilmDate=" + ilmDate +
        ", ilmArchiveFlag='" + ilmArchiveFlag + '\'' +
        ", modifiedBy='" + modifiedBy + '\'' +
        ", modifiedAt='" + modifiedAt + '\'' +
        ", currency='" + currency + '\'' +
        ", unpaidAmount=" + unpaidAmount +
        '}';
  }
}
