//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 10:12:18 AM BST 
//


package com.worldpay.servicing.gateway.inbound.model.approvews;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for servicingItemRequest complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="servicingItemRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="callInfo"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="callId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="callType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="creationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="caseInformation"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="caseId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="approvedBy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="serviceItem"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="serviceId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="serviceType" type="{http://model.inbound.gateway.servicing.worldpay.com/approvews}serviceItemType"/&gt;
 *                   &lt;element name="status" type="{http://model.inbound.gateway.servicing.worldpay.com/approvews}serviceItemStatus"/&gt;
 *                   &lt;element name="approvalDecisionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *                   &lt;element name="rejectionReason" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "servicingItemRequest", propOrder = {
    "callInfo",
    "caseInformation",
    "serviceItem"
})
public class ServicingItemRequest {

  @XmlElement(required = true)
  protected ServicingItemRequest.CallInfo callInfo;

  @XmlElement(required = true)
  protected ServicingItemRequest.CaseInformation caseInformation;

  @XmlElement(required = true)
  protected ServicingItemRequest.ServiceItem serviceItem;

  /**
   * Gets the value of the callInfo property.
   *
   * @return possible object is {@link ServicingItemRequest.CallInfo }
   */
  public ServicingItemRequest.CallInfo getCallInfo() {
    return callInfo;
  }

  /**
   * Sets the value of the callInfo property.
   *
   * @param value allowed object is {@link ServicingItemRequest.CallInfo }
   */
  public void setCallInfo(ServicingItemRequest.CallInfo value) {
    this.callInfo = value;
  }

  /**
   * Gets the value of the caseInformation property.
   *
   * @return possible object is {@link ServicingItemRequest.CaseInformation }
   */
  public ServicingItemRequest.CaseInformation getCaseInformation() {
    return caseInformation;
  }

  /**
   * Sets the value of the caseInformation property.
   *
   * @param value allowed object is {@link ServicingItemRequest.CaseInformation }
   */
  public void setCaseInformation(ServicingItemRequest.CaseInformation value) {
    this.caseInformation = value;
  }

  /**
   * Gets the value of the serviceItem property.
   *
   * @return possible object is {@link ServicingItemRequest.ServiceItem }
   */
  public ServicingItemRequest.ServiceItem getServiceItem() {
    return serviceItem;
  }

  /**
   * Sets the value of the serviceItem property.
   *
   * @param value allowed object is {@link ServicingItemRequest.ServiceItem }
   */
  public void setServiceItem(ServicingItemRequest.ServiceItem value) {
    this.serviceItem = value;
  }


  /**
   * <p>Java class for anonymous complex type.
   *
   * <p>The following schema fragment specifies the expected content contained within this class.
   *
   * <pre>
   * &lt;complexType&gt;
   *   &lt;complexContent&gt;
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
   *       &lt;sequence&gt;
   *         &lt;element name="callId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
   *         &lt;element name="callType" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
   *         &lt;element name="creationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {
      "callId",
      "callType",
      "creationDate"
  })
  public static class CallInfo {

    @XmlElement(required = true)
    protected String callId;

    @XmlElement(required = true, defaultValue = "approveServiceItem")
    protected String callType;

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;

    /**
     * Gets the value of the callId property.
     *
     * @return possible object is {@link String }
     */
    public String getCallId() {
      return callId;
    }

    /**
     * Sets the value of the callId property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCallId(String value) {
      this.callId = value;
    }

    /**
     * Gets the value of the callType property.
     *
     * @return possible object is {@link String }
     */
    public String getCallType() {
      return callType;
    }

    /**
     * Sets the value of the callType property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCallType(String value) {
      this.callType = value;
    }

    /**
     * Gets the value of the creationDate property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getCreationDate() {
      return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     */
    public void setCreationDate(XMLGregorianCalendar value) {
      this.creationDate = value;
    }

  }


  /**
   * <p>Java class for anonymous complex type.
   *
   * <p>The following schema fragment specifies the expected content contained within this class.
   *
   * <pre>
   * &lt;complexType&gt;
   *   &lt;complexContent&gt;
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
   *       &lt;sequence&gt;
   *         &lt;element name="caseId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
   *         &lt;element name="approvedBy" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {
      "caseId",
      "approvedBy"
  })
  public static class CaseInformation {

    @XmlElement(required = true)
    protected String caseId;

    @XmlElement(required = true)
    protected String approvedBy;

    /**
     * Gets the value of the caseId property.
     *
     * @return possible object is {@link String }
     */
    public String getCaseId() {
      return caseId;
    }

    /**
     * Sets the value of the caseId property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCaseId(String value) {
      this.caseId = value;
    }

    /**
     * Gets the value of the approvedBy property.
     *
     * @return possible object is {@link String }
     */
    public String getApprovedBy() {
      return approvedBy;
    }

    /**
     * Sets the value of the approvedBy property.
     *
     * @param value allowed object is {@link String }
     */
    public void setApprovedBy(String value) {
      this.approvedBy = value;
    }

  }


  /**
   * <p>Java class for anonymous complex type.
   *
   * <p>The following schema fragment specifies the expected content contained within this class.
   *
   * <pre>
   * &lt;complexType&gt;
   *   &lt;complexContent&gt;
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
   *       &lt;sequence&gt;
   *         &lt;element name="serviceId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
   *         &lt;element name="serviceType" type="{http://model.inbound.gateway.servicing.worldpay.com/approvews}serviceItemType"/&gt;
   *         &lt;element name="status" type="{http://model.inbound.gateway.servicing.worldpay.com/approvews}serviceItemStatus"/&gt;
   *         &lt;element name="approvalDecisionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
   *         &lt;element name="rejectionReason" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
   *       &lt;/sequence&gt;
   *     &lt;/restriction&gt;
   *   &lt;/complexContent&gt;
   * &lt;/complexType&gt;
   * </pre>
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {
      "serviceId",
      "serviceType",
      "status",
      "approvalDecisionDate",
      "rejectionReason"
  })
  public static class ServiceItem {

    @XmlElement(required = true, nillable = true)
    protected String serviceId;

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ServiceItemType serviceType;

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ServiceItemStatus status;

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar approvalDecisionDate;

    @XmlElement(required = true, nillable = true)
    protected String rejectionReason;

    /**
     * Gets the value of the serviceId property.
     *
     * @return possible object is {@link String }
     */
    public String getServiceId() {
      return serviceId;
    }

    /**
     * Sets the value of the serviceId property.
     *
     * @param value allowed object is {@link String }
     */
    public void setServiceId(String value) {
      this.serviceId = value;
    }

    /**
     * Gets the value of the serviceType property.
     *
     * @return possible object is {@link ServiceItemType }
     */
    public ServiceItemType getServiceType() {
      return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     *
     * @param value allowed object is {@link ServiceItemType }
     */
    public void setServiceType(ServiceItemType value) {
      this.serviceType = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return possible object is {@link ServiceItemStatus }
     */
    public ServiceItemStatus getStatus() {
      return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is {@link ServiceItemStatus }
     */
    public void setStatus(ServiceItemStatus value) {
      this.status = value;
    }

    /**
     * Gets the value of the approvalDecisionDate property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getApprovalDecisionDate() {
      return approvalDecisionDate;
    }

    /**
     * Sets the value of the approvalDecisionDate property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     */
    public void setApprovalDecisionDate(XMLGregorianCalendar value) {
      this.approvalDecisionDate = value;
    }

    /**
     * Gets the value of the rejectionReason property.
     *
     * @return possible object is {@link String }
     */
    public String getRejectionReason() {
      return rejectionReason;
    }

    /**
     * Sets the value of the rejectionReason property.
     *
     * @param value allowed object is {@link String }
     */
    public void setRejectionReason(String value) {
      this.rejectionReason = value;
    }

  }

}
