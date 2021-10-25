package com.worldpay.servicing.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.worldpay.servicing.model.entity.enums.BillCorrectionType;
import com.worldpay.servicing.model.entity.enums.BooleanFlag;
import com.worldpay.servicing.model.entity.enums.ServiceItemStatus;
import com.worldpay.servicing.model.profile.JsonProfile.ServiceItemView;
import com.worldpay.servicing.model.util.BillIdSerializer;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class BillCorrectionDTO implements EntityLifecycle, EntityCommon {

  private static final String THE_FIELD_MUST_BE_LESS_THAN = " field must be less than or equals";

  private static final String CHARACTERS = "characters";

  private static final String MUST_BE_NOT_NULL = " must not be NULL";

  @Length(max = 12, message = "correctionId" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  @JsonProperty("correctionId")
  private String billCorrId;

  @JsonView(ServiceItemView.class)
  private Date uploadDate;

  @JsonView(ServiceItemView.class)
  @JsonProperty("approvalDecisionDate")
  private Date approvalDate;

  @NotNull(message = "status" + MUST_BE_NOT_NULL)
  @JsonView(ServiceItemView.class)
  private ServiceItemStatus status;

  @Length(max = 30, message = "rejectionReason" + THE_FIELD_MUST_BE_LESS_THAN + " 30 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String rejectionReason;

  @Length(max = 12, message = "parentCorrectionId" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  @JsonProperty("parentCorrectionId")
  private String parentBillCorrId;

  @NotNull(message = "correctionType" + MUST_BE_NOT_NULL)
  @JsonView(ServiceItemView.class)
  @JsonProperty("correctionType")
  private BillCorrectionType type;

  @JsonView(ServiceItemView.class)
  @JsonProperty("correctionTypeDesc")
  private String typeDescription;

  @Length(max = 256, message = "reason" + THE_FIELD_MUST_BE_LESS_THAN + " 256 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String reason;

  @NotNull(message = "partyIdentifier" + MUST_BE_NOT_NULL)
  @Length(max = 12, message = "partyIdentifier" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  @JsonProperty("partyIdentifier")
  private String partyId;

  @NotNull(message = "billId" + MUST_BE_NOT_NULL)
  @Length(max = 64, message = "billId" + THE_FIELD_MUST_BE_LESS_THAN + " 64 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  @JsonSerialize(using = BillIdSerializer.class)
  private String billId;

  @NotNull(message = "alternateBillId" + MUST_BE_NOT_NULL)
  @Length(max = 30, message = "alternateBillId" + THE_FIELD_MUST_BE_LESS_THAN + " 30 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  @JsonProperty("alternateBillId")
  private String altBillId;

  @NotNull(message = "lineId" + MUST_BE_NOT_NULL)
  @Length(max = 12, message = "lineId" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  @JsonProperty("lineId")
  private String lineId;

  @NotNull(message = "acctType" + MUST_BE_NOT_NULL)
  @Length(max = 4, message = "acctType" + THE_FIELD_MUST_BE_LESS_THAN + " 4 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  @JsonProperty("acctType")
  private String accountType;

  @NotNull(message = "Settlement Line Value" + MUST_BE_NOT_NULL)
  @Digits(integer = 11, fraction = 2,
      message = "Settlement Line Value must have at most 11 digits and 2 decimals")
  @JsonView(ServiceItemView.class)
  private BigDecimal lineAmount;

  @NotNull(message = "reUseDueDate" + MUST_BE_NOT_NULL)
  @JsonProperty("reUseDueDate")
  private BooleanFlag reuseDueDate;

  @JsonProperty("paidInvoice")
  private BooleanFlag paidInvoice;

  @NotNull(message = "currency" + MUST_BE_NOT_NULL)
  @Length(max = 3, message = "currency" + THE_FIELD_MUST_BE_LESS_THAN + " 3 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String currency;

  @NotNull(message = "Unpaid Amount" + MUST_BE_NOT_NULL)
  @Digits(integer = 11, fraction = 2,
      message = "Unpaid Amount must have at most 11 digits and 2 decimals")
  @JsonView(ServiceItemView.class)
  private BigDecimal unpaidAmount;

  @NotNull(message = "latestBankingEntryEvent" + MUST_BE_NOT_NULL)
  @Digits(integer = 20, fraction = 0,
      message = "latestBankingEntryEvent must be an integer with maximum 20 digits ")
  @JsonView(ServiceItemView.class)
  private BigDecimal latestBankingEntryEvent;

  @NotNull(message = "caseId" + MUST_BE_NOT_NULL)
  @Length(max = 12, message = "caseId" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  private String caseId;

  @NotNull(message = "createdBy" + MUST_BE_NOT_NULL)
  @Length(max = 50, message = "createdBy" + THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String createdBy;

  @Length(max = 50, message = "approvedBy" + THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String approvedBy;

  private Date ilmDate;

  @Pattern(regexp = "Y|N", message = "ilmArchiveFlag must be Y or N")
  @JsonView(ServiceItemView.class)
  private String ilmArchiveFlag;

  @JsonView(ServiceItemView.class)
  private Date modifiedAt;

  @JsonView(ServiceItemView.class)
  private String modifiedBy;

  @JsonView(ServiceItemView.class)
  private String approvalId;

  public String getBillCorrId() {
    return billCorrId;
  }

  public void setBillCorrId(String billCorrId) {
    this.billCorrId = billCorrId;
  }

  public Date getUploadDate() {
    return uploadDate;
  }

  @Override
  public void setUploadDate(Date uploadDate) {
    this.uploadDate = uploadDate;
  }

  public Date getApprovalDate() {
    return approvalDate;
  }

  public void setApprovalDate(Date approvalDate) {
    this.approvalDate = approvalDate;
  }

  public ServiceItemStatus getStatus() {
    return status;
  }

  @Override
  public void setStatus(ServiceItemStatus status) {
    this.status = status;
  }

  public String getRejectionReason() {
    return rejectionReason;
  }

  @Override
  public void setRejectionReason(String rejectionReason) {
    this.rejectionReason = rejectionReason;
  }

  public String getParentBillCorrId() {
    return parentBillCorrId;
  }

  public void setParentBillCorrId(String parentBillCorrId) {
    this.parentBillCorrId = parentBillCorrId;
  }

  public BillCorrectionType getType() {
    return type;
  }

  public void setType(BillCorrectionType type) {
    this.type = type;
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

  public String getCaseId() {
    return caseId;
  }

  public void setCaseId(String caseId) {
    this.caseId = caseId;
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

  @Override
  public void setApprovedBy(String approvedBy) {
    this.approvedBy = approvedBy;
  }

  public Date getIlmDate() {
    return ilmDate;
  }

  @Override
  public void setIlmDate(Date ilmDate) {
    this.ilmDate = ilmDate;
  }

  public String getIlmArchiveFlag() {
    return ilmArchiveFlag;
  }

  @Override
  public void setIlmArchiveFlag(String ilmArchiveFlag) {
    this.ilmArchiveFlag = ilmArchiveFlag;
  }

  public Date getModifiedAt() {
    return modifiedAt;
  }

  @Override
  public void setModifiedAt(Date modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  @Override
  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
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
  public String getId() {
    return this.billCorrId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BillCorrectionDTO that = (BillCorrectionDTO) o;
    return Stream.of(Objects.equals(billCorrId, that.billCorrId),
        Objects.equals(uploadDate, that.uploadDate),
        Objects.equals(billId, that.billId),
        Objects.equals(lineId, that.lineId),
        Objects.equals(altBillId, that.altBillId)).allMatch(e -> e);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        billCorrId,
        uploadDate,
        altBillId,
        billId,
        lineId);
  }

  @Override
  public String toString() {
    return "BillCorrectionDTO{" +
        "correctionId=" + billCorrId +
        ", uploadDate=" + uploadDate +
        ", approvalDate=" + approvalDate +
        ", status=" + status +
        ", rejectionReason='" + rejectionReason + '\'' +
        ", parentBillCorrId='" + parentBillCorrId + '\'' +
        ", type=" + type +
        ", typeDescription=" + typeDescription +
        ", reason='" + reason + '\'' +
        ", partyId='" + partyId + '\'' +
        ", billId='" + billId + '\'' +
        ", altBillId='" + altBillId + '\'' +
        ", lineId='" + lineId + '\'' +
        ", accountType='" + accountType + '\'' +
        ", lineAmount=" + lineAmount +
        ", reuseDueDate=" + reuseDueDate +
        ", paidInvoice=" + paidInvoice +
        ", currency='" + currency + '\'' +
        ", unpaidAmount=" + unpaidAmount +
        ", latestBankingEntryEvent=" + latestBankingEntryEvent +
        ", caseId='" + caseId + '\'' +
        ", createdBy='" + createdBy + '\'' +
        ", approvedBy='" + approvedBy + '\'' +
        ", ilmDate=" + ilmDate +
        ", ilmArchiveFlag='" + ilmArchiveFlag + '\'' +
        ", modifiedAt=" + modifiedAt +
        ", modifiedBy='" + modifiedBy + '\'' +
        '}';
  }
}
