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
@Table(name = "CBE_SERVICE_ITEM_HIERARCHY")
public class ServiceItemHierarchy {

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
  @JoinColumn(name = "SERV_ITEM_CLASS", referencedColumnName = "SERVICE_CLASS_ID")
  private ServiceClassifier serviceClassifier;

  @Id
  @Column(name = "SERV_ITEM_CD")
  private String serviceItemCode;

  @JsonProperty("serviceTypeCode")
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
      property = "serviceTypeCode")
  @JsonIdentityReference(alwaysAsId = true)
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "SERV_ITEM_TYPE", referencedColumnName = "SERVICE_TYPE_CD")
  private ServicingAdminRule servicingAdminRule;

  @Column(name = "SERV_ITEM_TYPE_DESCR")
  private String serviceItemTypeDescription;

  @Column(name = "SERV_ITEM_REASON")
  private String serviceItemReason;

  @Column(name = "SERV_ITEM_REASON_DESCR")
  private String serviceItemReasonDescription;

  @Column(name = "SERV_ITEM_SUB_REASON")
  private String serviceItemSubReason;

  @Column(name = "SERV_ITEM_SUB_REASON_DESCR")
  private String serviceItemSubReasonDescription;

  @Column(name = "VALID_FROM")
  private Date validFrom;

  @Column(name = "VALID_TO")
  private Date validTo;

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

  public String getServiceItemCode() {
    return serviceItemCode;
  }

  public void setServiceItemCode(String serviceItemCode) {
    this.serviceItemCode = serviceItemCode;
  }

  public ServicingAdminRule getServicingAdminRule() {
    return servicingAdminRule;
  }

  public void setServicingAdminRule(
      ServicingAdminRule servicingAdminRule) {
    this.servicingAdminRule = servicingAdminRule;
  }

  public String getServiceItemTypeDescription() {
    return serviceItemTypeDescription;
  }

  public void setServiceItemTypeDescription(String serviceItemTypeDescription) {
    this.serviceItemTypeDescription = serviceItemTypeDescription;
  }

  public String getServiceItemReason() {
    return serviceItemReason;
  }

  public void setServiceItemReason(String serviceItemReason) {
    this.serviceItemReason = serviceItemReason;
  }

  public String getServiceItemReasonDescription() {
    return serviceItemReasonDescription;
  }

  public void setServiceItemReasonDescription(String serviceItemReasonDescription) {
    this.serviceItemReasonDescription = serviceItemReasonDescription;
  }

  public String getServiceItemSubReason() {
    return serviceItemSubReason;
  }

  public void setServiceItemSubReason(String serviceItemSubReason) {
    this.serviceItemSubReason = serviceItemSubReason;
  }

  public String getServiceItemSubReasonDescription() {
    return serviceItemSubReasonDescription;
  }

  public void setServiceItemSubReasonDescription(String serviceItemSubReasonDescription) {
    this.serviceItemSubReasonDescription = serviceItemSubReasonDescription;
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

  @Override
  public String toString() {
    return "ServiceItemHierarchy{" +
        "service=" + service +
        ", serviceClassifier=" + serviceClassifier +
        ", serviceItemCode='" + serviceItemCode + '\'' +
        '}';
  }
}
