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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="servicingItemRequest" type="{http://model.inbound.gateway.servicing.worldpay.com/approvews}servicingItemRequest"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "servicingItemRequest"
})
@XmlRootElement(name = "approveServicingItemRequest")
public class ApproveServicingItemRequest {

  @XmlElement(required = true)
  protected ServicingItemRequest servicingItemRequest;

  /**
   * Gets the value of the servicingItemRequest property.
   *
   * @return possible object is {@link ServicingItemRequest }
   */
  public ServicingItemRequest getServicingItemRequest() {
    return servicingItemRequest;
  }

  /**
   * Sets the value of the servicingItemRequest property.
   *
   * @param value allowed object is {@link ServicingItemRequest }
   */
  public void setServicingItemRequest(ServicingItemRequest value) {
    this.servicingItemRequest = value;
  }

}