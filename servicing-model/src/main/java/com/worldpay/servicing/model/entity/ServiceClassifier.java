package com.worldpay.servicing.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CBE_SERVICE_CLASSIFIER")
public class ServiceClassifier {

  @Id
  @Column(name = "SERVICE_CLASS_ID")
  private String serviceClassId;

  @Column(name = "SERVICE_CLASS_NAME")
  private String serviceClassName;

  @JsonProperty("serviceId")
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "serviceId")
  @JsonIdentityReference(alwaysAsId = true)
  @OneToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "SERVICE_ID", referencedColumnName = "SERVICE_ID")
  private Service service;

  public String getServiceClassId() {
    return serviceClassId;
  }

  public void setServiceClassId(String serviceClassId) {
    this.serviceClassId = serviceClassId;
  }

  public String getServiceClassName() {
    return serviceClassName;
  }

  public void setServiceClassName(String serviceClassName) {
    this.serviceClassName = serviceClassName;
  }

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  @Override
  public String toString() {
    return "ServiceClassifier{" +
        "serviceClassId='" + serviceClassId + '\'' +
        ", serviceClassName='" + serviceClassName + '\'' +
        ", serviceId='" + service.getServiceId() + '\'' +
        '}';
  }
}
