package com.worldpay.servicing.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CBE_SERVICE")
public class Service {

  @Id
  @Column(name = "SERVICE_ID")
  private String serviceId;

  @Column(name = "SERVICE_NAME")
  private String serviceName;

  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  @Override
  public String toString() {
    return "Service{" +
        "serviceId='" + serviceId + '\'' +
        ", serviceName='" + serviceName + '\'' +
        '}';
  }
}
