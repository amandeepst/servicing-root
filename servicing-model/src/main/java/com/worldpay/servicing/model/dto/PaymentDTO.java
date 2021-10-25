package com.worldpay.servicing.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.worldpay.servicing.model.entity.enums.ServiceItemStatus;
import com.worldpay.servicing.model.profile.JsonProfile.ServiceItemView;
import com.worldpay.servicing.model.util.BillIdSerializer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class PaymentDTO implements EntityLifecycle, EntityCommon {

  private static final String THE_FIELD_MUST_BE_LESS_THAN = " field must be less than or equals";

  private static final String MUST_BE_NOT_NULL = " must not be NULL";

  private static final String CHARACTERS = "characters";

  @Length(max = 12, message = "paymentId" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String paymentId;

  @Length(max = 12, message = "parentPaymentId" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String parentPaymentId;

  @JsonView(ServiceItemView.class)
  private Date uploadDate;

  @NotNull(message = "status" + MUST_BE_NOT_NULL)
  @JsonView(ServiceItemView.class)
  @JsonProperty("status")
  private ServiceItemStatus paymentStatus;

  @JsonView(ServiceItemView.class)
  private Date approvalDate;

  @Length(max = 30, message = "rejectionReason" + THE_FIELD_MUST_BE_LESS_THAN + " 30 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String rejectionReason;

  @NotNull(message = "status" + MUST_BE_NOT_NULL)
  @Length(max = 3, message = "status" + THE_FIELD_MUST_BE_LESS_THAN + " 3 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String paymentClass;

  @NotNull(message = "totalPaymentAmount" + MUST_BE_NOT_NULL)
  @JsonView(ServiceItemView.class)
  private BigDecimal totalPaymentAmount;

  @Length(max = 36, message = "paymentReference" + THE_FIELD_MUST_BE_LESS_THAN + " 36 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String paymentReference;

  @NotNull(message = "partyIdentifier" + MUST_BE_NOT_NULL)
  @Length(max = 12, message = "partyIdentifier" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  @JsonProperty("partyId")
  private String partyIdentifier;

  @NotNull(message = "paymentChannel" + MUST_BE_NOT_NULL)
  @Length(max = 30, message = "paymentChannel" + THE_FIELD_MUST_BE_LESS_THAN + " 30 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String paymentChannel;

  @JsonView(ServiceItemView.class)
  private Date dueDate;

  @JsonView(ServiceItemView.class)
  private Date billDate;

  @NotNull(message = "Settlement Line Value" + MUST_BE_NOT_NULL)
  @Digits(integer = 11, fraction = 2,
      message = "Settlement Line Value must have at most 11 digits and 2 decimals")
  @JsonView(ServiceItemView.class)
  @JsonProperty("lineAmount")
  private BigDecimal lineAmount;

  @NotNull(message = "Paid Amount" + MUST_BE_NOT_NULL)
  @Digits(integer = 11, fraction = 2,
      message = "Paid Amount must have at most 11 digits and 2 decimals")
  @JsonView(ServiceItemView.class)
  @JsonProperty("paidAmt")
  private BigDecimal paidAmount;

  @NotNull(message = "Unpaid Amount" + MUST_BE_NOT_NULL)
  @Digits(integer = 11, fraction = 2,
      message = "Unpaid Amount must have at most 11 digits and 2 decimals")
  @JsonView(ServiceItemView.class)
  @JsonProperty("unpaidAmount")
  private BigDecimal unpaidAmount;

  @NotNull(message = "currency" + MUST_BE_NOT_NULL)
  @Length(max = 3, message = "currency" + THE_FIELD_MUST_BE_LESS_THAN + " 3 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String currency;

  @NotNull(message = "latestBankingEntryEvent" + MUST_BE_NOT_NULL)
  @Digits(integer = 20, fraction = 0,
      message = "latestBankingEntryEvent must be an integer with maximum 20 digits ")
  @JsonView(ServiceItemView.class)
  private BigDecimal latestBankingEntryEvent;

  @Length(max = 10, message = "bankAcctRef" + THE_FIELD_MUST_BE_LESS_THAN + " 10 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String bankAcctRef;

  @NotNull(message = "lineId" + MUST_BE_NOT_NULL)
  @Length(max = 12, message = "lineId" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String lineId;

  @NotNull(message = "billId" + MUST_BE_NOT_NULL)
  @Length(max = 64, message = "billId" + THE_FIELD_MUST_BE_LESS_THAN + " 64 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  @JsonSerialize(using = BillIdSerializer.class)
  private String billId;

  @NotNull(message = "accountType" + MUST_BE_NOT_NULL)
  @Length(max = 4, message = "accountType" + THE_FIELD_MUST_BE_LESS_THAN + " 4 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String accountType;

  @NotNull(message = "alternateBillId" + MUST_BE_NOT_NULL)
  @Length(max = 30, message = "alternateBillId" + THE_FIELD_MUST_BE_LESS_THAN + " 30 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  @JsonProperty("altBillId")
  private String alternateBillId;

  @NotNull(message = "legalCounterparty" + MUST_BE_NOT_NULL)
  @Length(max = 12, message = "legalCounterparty" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String legalCounterparty;

  @NotNull(message = "legalCounterpartyDescr" + MUST_BE_NOT_NULL)
  @Length(max = 60, message = "legalCounterpartyDescr" + THE_FIELD_MUST_BE_LESS_THAN + " 60 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String legalCounterpartyDescr;

  @Length(max = 15, message = "bankingEntryStatus" + THE_FIELD_MUST_BE_LESS_THAN + " 15 " + CHARACTERS)
  private String bankingEntryStatus;

  @Length(max = 12, message = "adjustmentId" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String adjustmentId;

  @NotNull(message = "caseIdentifier" + MUST_BE_NOT_NULL)
  @Length(max = 12, message = "caseIdentifier" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  private String caseIdentifier;

  @NotNull(message = "createdBy" + MUST_BE_NOT_NULL)
  @Length(max = 50, message = "createdBy" + THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String createdBy;

  @Length(max = 50, message = "approvedBy" + THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String approvedBy;

  private Date ilmDate;

  @Pattern(regexp = "Y|N", message = "ilmArchiveFlag must be Y or N")
  private String ilmArchiveFlag;

  @NotNull(message = "paymentType" + MUST_BE_NOT_NULL)
  @Length(max = 50, message = "paymentType" + THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String paymentType;

  @JsonView(ServiceItemView.class)
  private Date modifiedAt;

  @JsonView(ServiceItemView.class)
  private String modifiedBy;

  @JsonView(ServiceItemView.class)
  private String approvalId;

  public String getLegalCounterpartyDescr() {
    return legalCounterpartyDescr;
  }

  public void setLegalCounterpartyDescr(String legalCounterpartyDescr) {
    this.legalCounterpartyDescr = legalCounterpartyDescr;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
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

  public Date getBillDate() {
    return billDate;
  }

  public void setBillDate(Date billDate) {
    this.billDate = billDate;
  }

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

  public String getAdjustmentId() {
    return adjustmentId;
  }

  public void setAdjustmentId(String adjustmentId) {
    this.adjustmentId = adjustmentId;
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

  public Date getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(Date modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  @Override
  public String getApprovalId() {
    return approvalId;
  }

  @Override
  public void setApprovalId(String approvalId) {
    this.approvalId = approvalId;
  }

  @Override
  @JsonIgnore
  public ServiceItemStatus getStatus() {
    return this.getPaymentStatus();
  }

  @Override
  @JsonIgnore
  public void setStatus(ServiceItemStatus newStatus) {
    this.setPaymentStatus(newStatus);
  }

  @Override
  @JsonIgnore
  public String getId() {
    return this.paymentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PaymentDTO)) {
      return false;
    }
    PaymentDTO that = (PaymentDTO) o;
    return Objects.equals(paymentId, that.paymentId)
        && Objects.equals(uploadDate, that.uploadDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        paymentId,
        uploadDate);
  }

}
