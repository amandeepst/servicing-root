package com.worldpay.servicing.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CBE_SERVICING_ADMIN_RULE")
public class ServicingAdminRule {

  @JsonProperty("serviceId")
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "serviceId")
  @JsonIdentityReference(alwaysAsId = true)
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "SERVICE", referencedColumnName = "SERVICE_ID")
  private Service service;

  @JsonProperty("serviceClassifierId")
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
      property = "serviceClassId")
  @JsonIdentityReference(alwaysAsId = true)
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "SERVICE_TYPE_CLASS", referencedColumnName = "SERVICE_CLASS_ID")
  private ServiceClassifier serviceClassifier;

  @Id
  @Column(name = "SERVICE_TYPE_CD")
  private String serviceTypeCode;

  @Column(name = "SERVICE_TYPE_DESCR")
  private String serviceTypeDescription;

  @Column(name = "ADJ_AMT_RO")
  private String adjustmentAmountRO;

  @Column(name = "BILL_PARTY_REQ")
  private String billPartyREQ;

  @Column(name = "BILL_PARTY_RO")
  private String billPartyRO;

  @Column(name = "BILL_DATE_FROM_REQ")
  private String billDateFromREQ;

  @Column(name = "BILL_DATE_FROM_RO")
  private String billDateFromRO;

  @Column(name = "BILL_DATE_TO_REQ")
  private String billDateToREQ;

  @Column(name = "BILL_DATE_TO_RO")
  private String billDateToRO;

  @Column(name = "CR_DR_REQ")
  private String creditDebitREQ;

  @Column(name = "CR_DR_RO")
  private String creditDebitRO;

  @Column(name = "FROM_CURRENCY_REQ")
  private String currencyFromREQ;

  @Column(name = "FROM_CURRENCY_RO")
  private String currencyFromRO;

  @Column(name = "TO_CURRENCY_REQ")
  private String currencyToREQ;

  @Column(name = "TO_CURRENCY_RO")
  private String currencyToRO;

  @Column(name = "FROM_PARTY_DEF")
  private String customerFromDEF;

  @Column(name = "FROM_PARTY_REQ")
  private String customerFromREQ;

  @Column(name = "FROM_PARTY_RO")
  private String customerFromRO;

  @Column(name = "TO_PARTY_DEF")
  private String customerToDEF;

  @Column(name = "TO_PARTY_REQ")
  private String customerToREQ;

  @Column(name = "TO_PARTY_RO")
  private String customerToRO;

  @Column(name = "FASTEST_FLG_REQ")
  private String fastestRouteREQ;

  @Column(name = "FASTEST_FLG_RO")
  private String fastestRouteRO;

  @Column(name = "ADHOC_BILL_REQ")
  private String immediateRouteREQ;

  @Column(name = "ADHOC_BILL_RO")
  private String immediateRouteRO;

  @Column(name = "DEBT_DT_REQ")
  private String debtDateREQ;

  @Column(name = "DEBT_DT_RO")
  private String debtDateRO;

  @Column(name = "FROM_LCP_REQ")
  private String legalCounterPartyFromREQ;

  @Column(name = "FROM_LCP_RO")
  private String legalCounterPartyFromRO;

  @Column(name = "TO_LCP_REQ")
  private String legalCounterPartyToREQ;

  @Column(name = "TO_LCP_RO")
  private String legalCounterPartyToRO;

  @Column(name = "METHOD_REQ")
  private String methodREQ;

  @Column(name = "METHOD_RO")
  private String methodRO;

  @Column(name = "TOTAL_PAY_AMT_REQ")
  private String paymentAmountREQ;

  @Column(name = "TOTAL_PAY_AMT_RO")
  private String paymentAmountRO;

  @Column(name = "PAY_CURRENCY_REQ")
  private String paymentCurrencyREQ;

  @Column(name = "PAY_CURRENCY_RO")
  private String paymentCurrencyRO;

  @Column(name = "PAYMENT_REF_REQ")
  private String paymentReferenceREQ;

  @Column(name = "PAYMENT_REF_RO")
  private String paymentReferenceRO;

  @Column(name = "PAY_NARRATIVE_REQ")
  private String statementNarrativeREQ;

  @Column(name = "PAY_NARRATIVE_RO")
  private String statementNarrativeRO;

  @Column(name = "FROM_SUB_ACCT_DEF")
  private String subLedgerFromDEF;

  @Column(name = "FROM_SUB_ACCT_REQ")
  private String subLedgerFromREQ;

  @Column(name = "FROM_SUB_ACCT_RO")
  private String subLedgerFromRO;

  @Column(name = "TO_SUB_ACCT_DEF")
  private String subLedgerToDEF;

  @Column(name = "TO_SUB_ACCT_REQ")
  private String subLedgerToREQ;

  @Column(name = "TO_SUB_ACCT_RO")
  private String subLedgerToRO;

  @Column(name = "VAL_FROM_BAL")
  private String validateFromBalance;

  @Column(name = "TYPE_REQ")
  private String typeReq;

  @Column(name = "TYPE_RO")
  private String typeRO;

  @Column(name = "REASON_REQ")
  private String reasonReq;

  @Column(name = "REASON_RO")
  private String reasonRO;

  @Column(name = "VALID_FROM")
  private Date validFrom;

  @Column(name = "VALID_TO")
  private Date validTo;

  @Column(name = "VERSION_COMMENTS")
  private String versionComments;


  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  public ServiceClassifier getServiceClassifier() {
    return serviceClassifier;
  }

  public void setServiceClassifier(ServiceClassifier serviceClassifier) {
    this.serviceClassifier = serviceClassifier;
  }

  public String getServiceTypeCode() {
    return serviceTypeCode;
  }

  public void setServiceTypeCode(String serviceTypeCode) {
    this.serviceTypeCode = serviceTypeCode;
  }

  public String getServiceTypeDescription() {
    return serviceTypeDescription;
  }

  public void setServiceTypeDescription(String serviceTypeDescription) {
    this.serviceTypeDescription = serviceTypeDescription;
  }

  public String getAdjustmentAmountRO() {
    return adjustmentAmountRO;
  }

  public void setAdjustmentAmountRO(String adjustmentAmountRO) {
    this.adjustmentAmountRO = adjustmentAmountRO;
  }

  public String getBillPartyREQ() {
    return billPartyREQ;
  }

  public void setBillPartyREQ(String billPartyREQ) {
    this.billPartyREQ = billPartyREQ;
  }

  public String getBillPartyRO() {
    return billPartyRO;
  }

  public void setBillPartyRO(String billPartyRO) {
    this.billPartyRO = billPartyRO;
  }

  public String getBillDateFromREQ() {
    return billDateFromREQ;
  }

  public void setBillDateFromREQ(String billDateFromREQ) {
    this.billDateFromREQ = billDateFromREQ;
  }

  public String getBillDateFromRO() {
    return billDateFromRO;
  }

  public void setBillDateFromRO(String billDateFromRO) {
    this.billDateFromRO = billDateFromRO;
  }

  public String getBillDateToREQ() {
    return billDateToREQ;
  }

  public void setBillDateToREQ(String billDateToREQ) {
    this.billDateToREQ = billDateToREQ;
  }

  public String getBillDateToRO() {
    return billDateToRO;
  }

  public void setBillDateToRO(String billDateToRO) {
    this.billDateToRO = billDateToRO;
  }

  public String getCreditDebitREQ() {
    return creditDebitREQ;
  }

  public void setCreditDebitREQ(String creditDebitREQ) {
    this.creditDebitREQ = creditDebitREQ;
  }

  public String getCreditDebitRO() {
    return creditDebitRO;
  }

  public void setCreditDebitRO(String creditDebitRO) {
    this.creditDebitRO = creditDebitRO;
  }

  public String getCurrencyFromREQ() {
    return currencyFromREQ;
  }

  public void setCurrencyFromREQ(String currencyFromREQ) {
    this.currencyFromREQ = currencyFromREQ;
  }

  public String getCurrencyFromRO() {
    return currencyFromRO;
  }

  public void setCurrencyFromRO(String currencyFromRO) {
    this.currencyFromRO = currencyFromRO;
  }

  public String getCurrencyToREQ() {
    return currencyToREQ;
  }

  public void setCurrencyToREQ(String currencyToREQ) {
    this.currencyToREQ = currencyToREQ;
  }

  public String getCurrencyToRO() {
    return currencyToRO;
  }

  public void setCurrencyToRO(String currencyToRO) {
    this.currencyToRO = currencyToRO;
  }

  public String getCustomerFromDEF() {
    return customerFromDEF;
  }

  public void setCustomerFromDEF(String customerFromDEF) {
    this.customerFromDEF = customerFromDEF;
  }

  public String getCustomerFromREQ() {
    return customerFromREQ;
  }

  public void setCustomerFromREQ(String customerFromREQ) {
    this.customerFromREQ = customerFromREQ;
  }

  public String getCustomerFromRO() {
    return customerFromRO;
  }

  public void setCustomerFromRO(String customerFromRO) {
    this.customerFromRO = customerFromRO;
  }

  public String getCustomerToDEF() {
    return customerToDEF;
  }

  public void setCustomerToDEF(String customerToDEF) {
    this.customerToDEF = customerToDEF;
  }

  public String getCustomerToREQ() {
    return customerToREQ;
  }

  public void setCustomerToREQ(String customerToREQ) {
    this.customerToREQ = customerToREQ;
  }

  public String getCustomerToRO() {
    return customerToRO;
  }

  public void setCustomerToRO(String customerToRO) {
    this.customerToRO = customerToRO;
  }

  public String getFastestRouteREQ() {
    return fastestRouteREQ;
  }

  public void setFastestRouteREQ(String fastestRouteREQ) {
    this.fastestRouteREQ = fastestRouteREQ;
  }

  public String getFastestRouteRO() {
    return fastestRouteRO;
  }

  public void setFastestRouteRO(String fastestRouteRO) {
    this.fastestRouteRO = fastestRouteRO;
  }

  public String getImmediateRouteREQ() {
    return immediateRouteREQ;
  }

  public void setImmediateRouteREQ(String immediateRouteREQ) {
    this.immediateRouteREQ = immediateRouteREQ;
  }

  public String getImmediateRouteRO() {
    return immediateRouteRO;
  }

  public void setImmediateRouteRO(String immediateRouteRO) {
    this.immediateRouteRO = immediateRouteRO;
  }

  public String getDebtDateREQ() {
    return debtDateREQ;
  }

  public void setDebtDateREQ(String debtDateREQ) {
    this.debtDateREQ = debtDateREQ;
  }

  public String getDebtDateRO() {
    return debtDateRO;
  }

  public void setDebtDateRO(String debtDateRO) {
    this.debtDateRO = debtDateRO;
  }

  public String getLegalCounterPartyFromREQ() {
    return legalCounterPartyFromREQ;
  }

  public void setLegalCounterPartyFromREQ(String legalCounterPartyFromREQ) {
    this.legalCounterPartyFromREQ = legalCounterPartyFromREQ;
  }

  public String getLegalCounterPartyFromRO() {
    return legalCounterPartyFromRO;
  }

  public void setLegalCounterPartyFromRO(String legalCounterPartyFromRO) {
    this.legalCounterPartyFromRO = legalCounterPartyFromRO;
  }

  public String getLegalCounterPartyToREQ() {
    return legalCounterPartyToREQ;
  }

  public void setLegalCounterPartyToREQ(String legalCounterPartyToREQ) {
    this.legalCounterPartyToREQ = legalCounterPartyToREQ;
  }

  public String getLegalCounterPartyToRO() {
    return legalCounterPartyToRO;
  }

  public void setLegalCounterPartyToRO(String legalCounterPartyToRO) {
    this.legalCounterPartyToRO = legalCounterPartyToRO;
  }

  public String getMethodREQ() {
    return methodREQ;
  }

  public void setMethodREQ(String methodREQ) {
    this.methodREQ = methodREQ;
  }

  public String getMethodRO() {
    return methodRO;
  }

  public void setMethodRO(String methodRO) {
    this.methodRO = methodRO;
  }

  public String getPaymentAmountREQ() {
    return paymentAmountREQ;
  }

  public void setPaymentAmountREQ(String paymentAmountREQ) {
    this.paymentAmountREQ = paymentAmountREQ;
  }

  public String getPaymentAmountRO() {
    return paymentAmountRO;
  }

  public void setPaymentAmountRO(String paymentAmountRO) {
    this.paymentAmountRO = paymentAmountRO;
  }

  public String getPaymentCurrencyREQ() {
    return paymentCurrencyREQ;
  }

  public void setPaymentCurrencyREQ(String paymentCurrencyREQ) {
    this.paymentCurrencyREQ = paymentCurrencyREQ;
  }

  public String getPaymentCurrencyRO() {
    return paymentCurrencyRO;
  }

  public void setPaymentCurrencyRO(String paymentCurrencyRO) {
    this.paymentCurrencyRO = paymentCurrencyRO;
  }

  public String getPaymentReferenceREQ() {
    return paymentReferenceREQ;
  }

  public void setPaymentReferenceREQ(String paymentReferenceREQ) {
    this.paymentReferenceREQ = paymentReferenceREQ;
  }

  public String getPaymentReferenceRO() {
    return paymentReferenceRO;
  }

  public void setPaymentReferenceRO(String paymentReferenceRO) {
    this.paymentReferenceRO = paymentReferenceRO;
  }

  public String getStatementNarrativeREQ() {
    return statementNarrativeREQ;
  }

  public void setStatementNarrativeREQ(String statementNarrativeREQ) {
    this.statementNarrativeREQ = statementNarrativeREQ;
  }

  public String getStatementNarrativeRO() {
    return statementNarrativeRO;
  }

  public void setStatementNarrativeRO(String statementNarrativeRO) {
    this.statementNarrativeRO = statementNarrativeRO;
  }

  public String getSubLedgerFromDEF() {
    return subLedgerFromDEF;
  }

  public void setSubLedgerFromDEF(String subLedgerFromDEF) {
    this.subLedgerFromDEF = subLedgerFromDEF;
  }

  public String getSubLedgerFromREQ() {
    return subLedgerFromREQ;
  }

  public void setSubLedgerFromREQ(String subLedgerFromREQ) {
    this.subLedgerFromREQ = subLedgerFromREQ;
  }

  public String getSubLedgerFromRO() {
    return subLedgerFromRO;
  }

  public void setSubLedgerFromRO(String subLedgerFromRO) {
    this.subLedgerFromRO = subLedgerFromRO;
  }

  public String getSubLedgerToDEF() {
    return subLedgerToDEF;
  }

  public void setSubLedgerToDEF(String subLedgerToDEF) {
    this.subLedgerToDEF = subLedgerToDEF;
  }

  public String getSubLedgerToREQ() {
    return subLedgerToREQ;
  }

  public void setSubLedgerToREQ(String subLedgerToREQ) {
    this.subLedgerToREQ = subLedgerToREQ;
  }

  public String getSubLedgerToRO() {
    return subLedgerToRO;
  }

  public void setSubLedgerToRO(String subLedgerToRO) {
    this.subLedgerToRO = subLedgerToRO;
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

  public String getVersionComments() {
    return versionComments;
  }

  public void setVersionComments(String versionComments) {
    this.versionComments = versionComments;
  }

  public String getValidateFromBalance() {
    return validateFromBalance;
  }

  public void setValidateFromBalance(String validateFromBalance) {
    this.validateFromBalance = validateFromBalance;
  }

  public String getTypeReq() {
    return typeReq;
  }

  public void setTypeReq(String typeReq) {
    this.typeReq = typeReq;
  }

  public String getTypeRO() {
    return typeRO;
  }

  public void setTypeRO(String typeRO) {
    this.typeRO = typeRO;
  }

  public String getReasonReq() {
    return reasonReq;
  }

  public void setReasonReq(String reasonReq) {
    this.reasonReq = reasonReq;
  }

  public String getReasonRO() {
    return reasonRO;
  }

  public void setReasonRO(String reasonRO) {
    this.reasonRO = reasonRO;
  }

  @Override
  public String toString() {
    return "ServicingAdminRule{" +
        "service=" + service +
        ", serviceClassifier=" + serviceClassifier +
        ", serviceTypeCode='" + serviceTypeCode + '\'' +
        ", serviceTypeDescription='" + serviceTypeDescription + '\'' +
        '}';
  }
}
