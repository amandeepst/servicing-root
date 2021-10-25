package com.worldpay.servicing.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.worldpay.servicing.model.entity.enums.ServiceItemStatus;
import com.worldpay.servicing.model.profile.JsonProfile.ServiceItemView;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class AdhocChargeDTO implements EntityLifecycle, EntityCommon {

  private static final String THE_FIELD_MUST_BE_LESS_THAN = " field must be less than or equals";

  private static final String THE_FIELD_MUST_BE_GREATER_THAN = " field must be greater than or equals";

  private static final String THE_FIELD_MUST_BE_POSITIVE_DECIMAL = " field must be a positive number with maximum 2 decimals";


  private static final String MUST_BE_NOT_NULL = " must not be NULL";

  private static final String CHARACTERS = "characters";

  @Length(max = 12, message = "adhocChargeId" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, groups = {SdsValidationAware.class,
      PciValidationAware.class})
  @JsonView(ServiceItemView.class)
  private String adhocChargeId;

  @JsonView(ServiceItemView.class)
  private Date uploadDate;

  @NotNull(message = "status" + MUST_BE_NOT_NULL, groups = {SdsValidationAware.class})
  @JsonView(ServiceItemView.class)
  private ServiceItemStatus status;

  @JsonView(ServiceItemView.class)
  private Date approvalDate;

  @Length(max = 30, message = "rejectionReason" + THE_FIELD_MUST_BE_LESS_THAN + " 30 " + CHARACTERS, groups = {SdsValidationAware.class,
      PciValidationAware.class})
  @JsonView(ServiceItemView.class)
  private String rejectionReason;

  @NotNull(message = "partyId" + MUST_BE_NOT_NULL, groups = {SdsValidationAware.class, PciValidationAware.class})
  @Length(max = 12, message = "partyId" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, groups = {SdsValidationAware.class,
      PciValidationAware.class})
  @JsonView(ServiceItemView.class)
  private String partyId;

  @NotNull(message = "chargeType" + MUST_BE_NOT_NULL, groups = {SdsValidationAware.class, PciValidationAware.class})
  @Length(max = 8, message = "chargeType" + THE_FIELD_MUST_BE_LESS_THAN + " 8 " + CHARACTERS, groups = {SdsValidationAware.class,
      PciValidationAware.class})
  @JsonView(ServiceItemView.class)
  private String chargeType;

  @NotNull(message = "quantity" + MUST_BE_NOT_NULL, groups = {SdsValidationAware.class, PciValidationAware.class})
  @Positive(message = "quantity" + THE_FIELD_MUST_BE_GREATER_THAN + " 1", groups = {SdsValidationAware.class, PciValidationAware.class})
  @JsonView(ServiceItemView.class)
  private BigDecimal quantity;

  @NotNull(message = "amount" + MUST_BE_NOT_NULL, groups = {PciValidationAware.class})
  @DecimalMin(value = "0.0", inclusive = false, message = "amount" + THE_FIELD_MUST_BE_POSITIVE_DECIMAL, groups = {
      PciValidationAware.class})
  @Digits(integer = 11, fraction = 2,
      message = "Amount must have at most 11 digits and 2 decimals", groups = {SdsValidationAware.class, PciValidationAware.class})
  @JsonView(ServiceItemView.class)
  private BigDecimal amount;

  @NotNull(message = "legalCounterparty" + MUST_BE_NOT_NULL, groups = {SdsValidationAware.class, PciValidationAware.class})
  @Length(max = 12, message = "legalCounterparty" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, groups = {SdsValidationAware.class,
      PciValidationAware.class})
  @JsonView(ServiceItemView.class)
  private String legalCounterparty;


  @NotNull(message = "legalCounterpartyDescr" + MUST_BE_NOT_NULL, groups = {SdsValidationAware.class})
  @Length(max = 60, message = "legalCounterpartyDescr" + THE_FIELD_MUST_BE_LESS_THAN + " 60 " + CHARACTERS, groups = {
      SdsValidationAware.class})
  @JsonView(ServiceItemView.class)
  private String legalCounterpartyDescr;

  @NotNull(message = "currency" + MUST_BE_NOT_NULL, groups = {PciValidationAware.class})
  @Length(max = 3, message = "currency" + THE_FIELD_MUST_BE_LESS_THAN + " 3 " + CHARACTERS, groups = {SdsValidationAware.class,
      PciValidationAware.class})
  @JsonView(ServiceItemView.class)
  private String currency;

  @NotNull(message = "immediateBillFlag" + MUST_BE_NOT_NULL, groups = {SdsValidationAware.class})
  @Pattern(regexp = "Y|N", message = "immediateBillFlag must be Y or N", groups = {SdsValidationAware.class})
  @JsonView(ServiceItemView.class)
  private String immediateBillFlag;

  @Length(max = 50, message = "adhocChargeReason" + THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS, groups = {SdsValidationAware.class,
      PciValidationAware.class})
  @JsonView(ServiceItemView.class)
  private String adhocChargeReason;

  @NotNull(message = "caseIdentifier" + MUST_BE_NOT_NULL, groups = {SdsValidationAware.class})
  @Length(max = 12, message = "caseIdentifier" + THE_FIELD_MUST_BE_LESS_THAN + " 12 " + CHARACTERS, groups = {SdsValidationAware.class})
  @JsonView(ServiceItemView.class)
  private String caseIdentifier;

  @NotNull(message = "createdBy" + MUST_BE_NOT_NULL, groups = {SdsValidationAware.class})
  @Length(max = 50, message = "createdBy" + THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS, groups = {SdsValidationAware.class})
  @JsonView(ServiceItemView.class)
  private String createdBy;

  @Length(max = 50, message = "approvedBy" + THE_FIELD_MUST_BE_LESS_THAN + " 50 " + CHARACTERS, groups = {SdsValidationAware.class})
  @JsonView(ServiceItemView.class)
  private String approvedBy;

  private Date ilmDate;

  private String ilmArchiveFlag;

  @NotNull(message = "chargeTypeDescr" + MUST_BE_NOT_NULL, groups = {SdsValidationAware.class})
  @Length(max = 256, message = "chargeTypeDescr" + THE_FIELD_MUST_BE_LESS_THAN + " 256 " + CHARACTERS, groups = {SdsValidationAware.class})
  @JsonView(ServiceItemView.class)
  private String chargeTypeDescr;

  @JsonView(ServiceItemView.class)
  private Date modifiedAt;

  @JsonView(ServiceItemView.class)
  private String modifiedBy;

  @JsonView(ServiceItemView.class)
  private String approvalId;

  @JsonView(ServiceItemView.class)
  public BigDecimal getChargeAmount() {
    if (amount != null && quantity != null) {
      return amount.multiply(quantity);
    }
    return null;
  }

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

  @Override
  @JsonIgnore
  public String getId() {
    return this.adhocChargeId;
  }

  public BigDecimal computeTotalAmount() {
    if (this.amount == null || this.quantity == null) {
      return BigDecimal.ZERO;
    }

    return this.amount.multiply(this.quantity);
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdhocChargeDTO that = (AdhocChargeDTO) o;
    return Objects.equals(adhocChargeId, that.adhocChargeId)
        && Objects.equals(uploadDate, that.uploadDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        adhocChargeId,
        uploadDate);
  }

  public interface SdsValidationAware {
    // validation group marker interface
  }

  public interface PciValidationAware {
    // validation group marker interface
  }
}
