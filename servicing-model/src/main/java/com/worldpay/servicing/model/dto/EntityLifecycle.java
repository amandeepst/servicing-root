package com.worldpay.servicing.model.dto;

import com.worldpay.servicing.model.entity.enums.ServiceItemStatus;

import java.util.Date;

public interface EntityLifecycle {

  ServiceItemStatus getStatus();

  void setStatus(ServiceItemStatus newStatus);

  String getRejectionReason();

  void setRejectionReason(String reason);

  String getApprovedBy();

  void setApprovedBy(String approver);

  Date getApprovalDate();

  void setApprovalDate(Date approvalDate);

  String getApprovalId();

  void setApprovalId(String approvalId);

}
