package com.worldpay.servicing.model.entity;

import com.worldpay.servicing.model.entity.enums.ParamType;
import com.worldpay.servicing.model.util.Identifiable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CBE_APP_PARAM")
public class AppParam implements Identifiable<String> {

  @Id
  @SequenceGenerator(name = "param_id_generator",
      allocationSize = 1,
      sequenceName = "CBE_APP_PARAM_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "param_id_generator")
  private Integer paramId;

  private String paramName;

  @Enumerated(EnumType.STRING)
  private ParamType paramType;

  private String paramStringVal;

  public Integer getParamId() {
    return paramId;
  }

  public void setParamId(Integer paramId) {
    this.paramId = paramId;
  }

  public String getParamName() {
    return paramName;
  }

  public void setParamName(String paramName) {
    this.paramName = paramName;
  }

  public ParamType getParamType() {
    return paramType;
  }

  public void setParamType(ParamType paramType) {
    this.paramType = paramType;
  }

  public String getParamStringVal() {
    return paramStringVal;
  }

  public void setParamStringVal(String paramStringVal) {
    this.paramStringVal = paramStringVal;
  }

  @Override
  public String getId() {
    return String.valueOf(paramId);
  }
}
