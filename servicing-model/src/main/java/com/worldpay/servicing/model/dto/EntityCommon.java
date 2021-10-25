package com.worldpay.servicing.model.dto;

import java.util.Date;

public interface EntityCommon {

  String getApprovedBy();

  Date getUploadDate();

  void setUploadDate(Date uploadDate);

  Date getIlmDate();

  void setIlmDate(Date ilmDate);

  String getIlmArchiveFlag();

  void setIlmArchiveFlag(String ilmArchiveFlag);

  Date getModifiedAt();

  void setModifiedAt(Date modifiedAt);

  String getModifiedBy();

  void setModifiedBy(String modifiedBy);

  String getId();

  Date getApprovalDate();

  void setApprovalDate(Date approvalDate);
}
