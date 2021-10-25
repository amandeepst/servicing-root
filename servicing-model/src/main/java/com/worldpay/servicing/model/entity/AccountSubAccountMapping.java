package com.worldpay.servicing.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CBE_ACCOUNT_SUBACCOUNT_MAPPING")
public class AccountSubAccountMapping {

  @Column(name = "ACCT_TYPE")
  private String accountType;

  @Id
  @Column(name = "SUBACCT_TYPE")
  private String subAccountType;

  @Column(name = "SUBACCT_DESCRIPTION")
  private String subAccountTypeDescription;

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public String getSubAccountType() {
    return subAccountType;
  }

  public void setSubAccountType(String subAccountType) {
    this.subAccountType = subAccountType;
  }

  public String getSubAccountTypeDescription() {
    return subAccountTypeDescription;
  }

  public void setSubAccountTypeDescription(String subAccountTypeDescription) {
    this.subAccountTypeDescription = subAccountTypeDescription;
  }

  @Override
  public String toString() {
    return "AccountSubAccountMapping{" +
        "accountType='" + accountType + '\'' +
        ", subAccountType='" + subAccountType + '\'' +
        ", subAccountTypeDescription='" + subAccountTypeDescription + '\'' +
        '}';
  }
}
