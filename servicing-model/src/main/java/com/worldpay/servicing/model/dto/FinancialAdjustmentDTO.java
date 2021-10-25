package com.worldpay.servicing.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.worldpay.servicing.model.entity.enums.ServiceItemStatus;
import com.worldpay.servicing.model.profile.JsonProfile.ServiceItemView;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class FinancialAdjustmentDTO implements EntityLifecycle, EntityCommon {

  private static final String THE_FIELD_MUST_BE_LESS_THAN = " field must be less than or equals";

  private static final String CHARACTERS = "characters";

  private static final String MUST_BE_NOT_NULL = " must not be NULL";

  @Length(max = 12, message = "adjustmentId" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String adjustmentId;

  @JsonView(ServiceItemView.class)
  private Date uploadDate;

  @JsonView(ServiceItemView.class)
  private Date approvalDate;

  @NotNull(message = "status" + MUST_BE_NOT_NULL)
  @JsonView(ServiceItemView.class)
  private ServiceItemStatus status;

  @Length(max = 30, message = "rejectionReason" + THE_FIELD_MUST_BE_LESS_THAN + " 30 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String rejectionReason;

  @NotNull(message = "adjustmentClass" + MUST_BE_NOT_NULL)
  @Length(max = 3, message = "adjustmentClass" + THE_FIELD_MUST_BE_LESS_THAN + " 3 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String adjustmentClass;

  @NotNull(message = "adjustmentCode" + MUST_BE_NOT_NULL)
  @Length(max = 8, message = "adjustmentCode" + THE_FIELD_MUST_BE_LESS_THAN + " 8 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String adjustmentCode;

  @NotNull(message = "adjustmentType" + MUST_BE_NOT_NULL)
  @Length(max = 8, message = "adjustmentType" + THE_FIELD_MUST_BE_LESS_THAN + " 8 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String adjustmentType;

  @NotNull(message = "adjustmentTypeDescr" + MUST_BE_NOT_NULL)
  @Length(max = 50, message = "adjustmentTypeDescr" + THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String adjustmentTypeDescr;

  @Length(max = 15, message = "adjustmentReason" + THE_FIELD_MUST_BE_LESS_THAN + " 15 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String adjustmentReason;

  @Length(max = 50, message = "adjustmentReasonDescr" + THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String adjustmentReasonDescr;

  @Length(max = 15, message = "adjustmentCause" + THE_FIELD_MUST_BE_LESS_THAN + " 15 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String adjustmentCause;

  @Length(max = 50, message = "adjustmentCauseDescr" + THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String adjustmentCauseDescr;

  @Length(max = 12, message = "fromPartyId" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String fromPartyId;

  @Length(max = 3, message = "fromCurrency" + THE_FIELD_MUST_BE_LESS_THAN + " 3 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String fromCurrency;

  @JsonView(ServiceItemView.class)
  private String fromSubAccount;

  @Length(max = 12, message = "fromLegalCounterparty" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String fromLegalCounterparty;

  @Length(max = 60, message = "fromLegalCounterpartyDescr" + THE_FIELD_MUST_BE_LESS_THAN + " 60 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String fromLegalCounterpartyDescr;

  @JsonView(ServiceItemView.class)
  @Digits(integer = 11, fraction = 2,
      message = "From Amount must have at most 11 digits and 2 decimals")
  private BigDecimal fromAmount;

  @Length(max = 12, message = "toPartyId" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String toPartyId;

  @Length(max = 3, message = "toCurrency" + THE_FIELD_MUST_BE_LESS_THAN + " 3 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String toCurrency;

  @JsonView(ServiceItemView.class)
  private String toSubAccount;

  @Length(max = 12, message = "toLegalCounterparty" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String toLegalCounterparty;

  @Length(max = 60, message = "toLegalCounterpartyDescr" + THE_FIELD_MUST_BE_LESS_THAN + " 60 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  private String toLegalCounterpartyDescr;

  @JsonView(ServiceItemView.class)
  @Digits(integer = 11, fraction = 2,
      message = "To Amount must have at most 11 digits and 2 decimals")
  private BigDecimal toAmount;

  @JsonView(ServiceItemView.class)
  private String creditDebit;

  @NotNull(message = "immediateBillFlag" + MUST_BE_NOT_NULL)
  @Pattern(regexp = "Y|N", message = "immediateBillFlag must be Y or N")
  @JsonView(ServiceItemView.class)
  private String immediateBillFlag;

  @NotNull(message = "fastestRouteFlag" + MUST_BE_NOT_NULL)
  @Pattern(regexp = "Y|N", message = "fastestRouteFlag must be Y or N")
  @JsonView(ServiceItemView.class)
  private String fastestRouteFlag;

  @Length(max = 18, message = "payNarrative" + THE_FIELD_MUST_BE_LESS_THAN + " 18 " + CHARACTERS)
  @JsonView(ServiceItemView.class)
  @JsonProperty("payNarrative")
  private String paymentNarrative;

  @JsonView(ServiceItemView.class)
  private String releaseReserveFlag;

  @JsonView(ServiceItemView.class)
  private String releaseWafFlag;

  @JsonView(ServiceItemView.class)
  private Date debtDate;

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

  private String ilmArchiveFlag;

  @JsonView(ServiceItemView.class)
  private Date modifiedAt;

  @JsonView(ServiceItemView.class)
  private String modifiedBy;

  @JsonView(ServiceItemView.class)
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

  public String getCreditDebit() {
    return creditDebit;
  }

  public void setCreditDebit(String creditDebit) {
    this.creditDebit = creditDebit;
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
  public String getId() {
    return this.adjustmentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FinancialAdjustmentDTO that = (FinancialAdjustmentDTO) o;
    return Objects.equals(adjustmentId, that.adjustmentId)
        && Objects.equals(uploadDate, that.uploadDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        adjustmentId,
        uploadDate);
  }
}
