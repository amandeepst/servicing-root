package com.worldpay.servicing.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CBE_PAYMENT_CHANNEL")
public class PaymentChannel {

  @Id
  @SequenceGenerator(name = "id_generator",
      allocationSize = 1,
      sequenceName = "CBE_PAYMENT_CHANNEL_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
  private Integer id;

  @Column(name = "PAYMENT_CHANNEL_IDENTIFIER")
  private String paymentChannelId;

  @Column(name = "PAYMENT_CHANNEL_NAME")
  private String paymentChannelName;

  @Column(name = "PAYMENT_METHOD_TYPE_ID")
  private String paymentMethodTypeId;

  @Column(name = "EXTERNAL_BANK_CHANNEL_ID")
  private String externalBankChannelId;

  @Column(name = "EXTERNAL_BANK_CHANNEL_NAME")
  private String externalBankChannelName;

  @Column(name = "PAYMENT_TYPE")
  private String paymentType;

  @Column(name = "VALID_FROM")
  private Date validFrom;

  @Column(name = "VALID_TO")
  private Date validTo;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPaymentChannelId() {
    return paymentChannelId;
  }

  public void setPaymentChannelId(String paymentChannelId) {
    this.paymentChannelId = paymentChannelId;
  }

  public String getPaymentChannelName() {
    return paymentChannelName;
  }

  public void setPaymentChannelName(String paymentChannelName) {
    this.paymentChannelName = paymentChannelName;
  }

  public String getPaymentMethodTypeId() {
    return paymentMethodTypeId;
  }

  public void setPaymentMethodTypeId(String paymentMethodTypeId) {
    this.paymentMethodTypeId = paymentMethodTypeId;
  }

  public String getExternalBankChannelId() {
    return externalBankChannelId;
  }

  public void setExternalBankChannelId(String externalBankChannelId) {
    this.externalBankChannelId = externalBankChannelId;
  }

  public String getExternalBankChannelName() {
    return externalBankChannelName;
  }

  public void setExternalBankChannelName(String externalBankChannelName) {
    this.externalBankChannelName = externalBankChannelName;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
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
    return "PaymentChannel{" +
        "paymentChannelId='" + paymentChannelId + '\'' +
        ", paymentChannelName='" + paymentChannelName + '\'' +
        ", paymentType='" + paymentType + '\'' +
        '}';
  }
}
