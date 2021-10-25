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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CBE_PAYMENT")
public class Payment implements Identifiable<String> {

  @Id
  @GenericGenerator(
      name = "payment-generator",
      strategy = "com.worldpay.servicing.model.util.StringSequenceIdentifier",
      parameters = {
          @org.hibernate.annotations.Parameter(name = "sequence_name", value = "CBE_PAYMENT_SEQ")
      })
  @GeneratedValue(generator = "payment-generator", strategy = GenerationType.SEQUENCE)
  private String paymentId;

  @Column(name = "UPLOAD_DTTM")
  private Date uploadDate;

  @Column(name = "STATUS_CD")
  @Enumerated(EnumType.STRING)
  private ServiceItemStatus paymentStatus = ServiceItemStatus.NOT_APPROVED;

  @Column(name = "APPROVAL_DTTM")
  private Date approvalDate;

  @Column(name = "REJ_REASON")
  private String rejectionReason;

  private String paymentClass;

  private String paymentType;

  @Column(name = "TOTAL_PAY_AMT")
  private BigDecimal totalPaymentAmount;

  @Column(name = "PAYMENT_REF")
  private String paymentReference;

  @Column(name = "PARTY_ID")
  private String partyIdentifier;

  private String paymentChannel;

  @Column(name = "DUE_DT")
  private Date dueDate;

  @Column(name = "BILL_DT")
  private Date billDate;

  @Column(name = "LINE_AMT")
  private BigDecimal lineAmount;

  @Column(name = "PAID_AMT")
  private BigDecimal paidAmount;

  @Column(name = "UNPAID_AMT")
  private BigDecimal unpaidAmount;

  @Column(name = "CURRENCY_CD")
  private String currency;

  @Column(name = "LATEST_BE_EVENT_ID")
  private BigDecimal latestBankingEntryEvent;

  private String lineId;

  @Column(name = "ACCT_TYPE")
  private String accountType;

  private String billId;

  @Column(name = "ALT_BILL_ID")
  private String alternateBillId;

  @Column(name = "LCP")
  private String legalCounterparty;

  @Column(name = "LCP_DESCR")
  private String legalCounterpartyDescr;

  private String bankingEntryStatus;

  private String bankAcctRef;

  @Column(name = "CASE_ID")
  private String caseIdentifier;

  private String createdBy;

  private String approvedBy;

  @Column(name = "ILM_DT")
  private Date ilmDate;

  @Column(name = "ILM_ARCH_SW")
  private String ilmArchiveFlag;

  @Column(name = "PARENT_PAYMENT_ID")
  private String parentPaymentId;

  @OneToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "ADJ_ID", referencedColumnName = "ADJ_ID")
  private FinancialAdjustment financialAdjustment;

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

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public Date getUploadDate() {
    return uploadDate;
  }

  public void setUploadDate(Date uploadDate) {
    this.uploadDate = uploadDate;
  }

  public ServiceItemStatus getPaymentStatus() {
    return paymentStatus;
  }

  public void setPaymentStatus(ServiceItemStatus paymentStatus) {
    this.paymentStatus = paymentStatus;
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

  public String getPaymentClass() {
    return paymentClass;
  }

  public void setPaymentClass(String paymentClass) {
    this.paymentClass = paymentClass;
  }

  public BigDecimal getTotalPaymentAmount() {
    return totalPaymentAmount;
  }

  public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
    this.totalPaymentAmount = totalPaymentAmount;
  }

  public String getPaymentReference() {
    return paymentReference;
  }

  public void setPaymentReference(String paymentReference) {
    this.paymentReference = paymentReference;
  }

  public String getPartyIdentifier() {
    return partyIdentifier;
  }

  public void setPartyIdentifier(String partyIdentifier) {
    this.partyIdentifier = partyIdentifier;
  }

  public String getPaymentChannel() {
    return paymentChannel;
  }

  public void setPaymentChannel(String paymentChannel) {
    this.paymentChannel = paymentChannel;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public Date getBillDate() { return billDate; }

  public void setBillDate(Date billDate) { this.billDate = billDate; }

  public BigDecimal getPaidAmount() {
    return paidAmount;
  }

  public void setPaidAmount(BigDecimal paidAmount) {
    this.paidAmount = paidAmount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public BigDecimal getLatestBankingEntryEvent() {
    return latestBankingEntryEvent;
  }

  public void setLatestBankingEntryEvent(BigDecimal latestBankingEntryEvent) {
    this.latestBankingEntryEvent = latestBankingEntryEvent;
  }

  public String getLineId() {
    return lineId;
  }

  public void setLineId(String lineId) {
    this.lineId = lineId;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public String getBillId() {
    return billId;
  }

  public void setBillId(String billId) {
    this.billId = billId;
  }

  public String getAlternateBillId() {
    return alternateBillId;
  }

  public void setAlternateBillId(String alternateBillId) {
    this.alternateBillId = alternateBillId;
  }

  public String getLegalCounterparty() {
    return legalCounterparty;
  }

  public void setLegalCounterparty(String legalCounterparty) {
    this.legalCounterparty = legalCounterparty;
  }

  public String getBankingEntryStatus() {
    return bankingEntryStatus;
  }

  public void setBankingEntryStatus(String bankingEntryStatus) {
    this.bankingEntryStatus = bankingEntryStatus;
  }

  public String getBankAcctRef() {
    return bankAcctRef;
  }

  public void setBankAcctRef(String bankAcctRef) {
    this.bankAcctRef = bankAcctRef;
  }

  public String getCaseIdentifier() {
    return caseIdentifier;
  }

  public void setCaseIdentifier(String caseIdentifier) {
    this.caseIdentifier = caseIdentifier;
  }

  public FinancialAdjustment getFinancialAdjustment() {
    return financialAdjustment;
  }

  public void setFinancialAdjustment(FinancialAdjustment financialAdjustment) {
    this.financialAdjustment = financialAdjustment;
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

  public String getParentPaymentId() {
    return parentPaymentId;
  }

  public void setParentPaymentId(String parentPaymentId) {
    this.parentPaymentId = parentPaymentId;
  }

  public BigDecimal getLineAmount() {
    return lineAmount;
  }

  public void setLineAmount(BigDecimal lineAmount) {
    this.lineAmount = lineAmount;
  }

  public BigDecimal getUnpaidAmount() {
    return unpaidAmount;
  }

  public void setUnpaidAmount(BigDecimal unpaidAmount) {
    this.unpaidAmount = unpaidAmount;
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
    return "Payment [paymentId="
        + paymentId
        + ", paymentStatus="
        + paymentStatus
        + ", paymentClass="
        + paymentClass
        + ", totalPaymentAmount="
        + totalPaymentAmount + "]";
  }

  @Override
  public String getId() {
    return this.paymentId;
  }
}
