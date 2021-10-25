//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.10.21 at 02:15:02 PM BST 
//


package com.worldpay.servicing.gateway.outbound.model.partyhierarchy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PARTY complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PARTY"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EXTERNAL_MERCHANT_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SIEBEL_ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LEGAL_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TRADING_NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PATH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PARTY", propOrder = {
    "externalmerchantid",
    "siebelid",
    "legalname",
    "tradingname",
    "path"
})
public class PARTY {

  @XmlElement(name = "EXTERNAL_MERCHANT_ID")
  protected String externalmerchantid;

  @XmlElement(name = "SIEBEL_ID")
  protected String siebelid;

  @XmlElement(name = "LEGAL_NAME")
  protected String legalname;

  @XmlElement(name = "TRADING_NAME")
  protected String tradingname;

  @XmlElement(name = "PATH")
  protected String path;

  /**
   * Gets the value of the externalmerchantid property.
   *
   * @return possible object is {@link String }
   */
  public String getEXTERNALMERCHANTID() {
    return externalmerchantid;
  }

  /**
   * Sets the value of the externalmerchantid property.
   *
   * @param value allowed object is {@link String }
   */
  public void setEXTERNALMERCHANTID(String value) {
    this.externalmerchantid = value;
  }

  /**
   * Gets the value of the siebelid property.
   *
   * @return possible object is {@link String }
   */
  public String getSIEBELID() {
    return siebelid;
  }

  /**
   * Sets the value of the siebelid property.
   *
   * @param value allowed object is {@link String }
   */
  public void setSIEBELID(String value) {
    this.siebelid = value;
  }

  /**
   * Gets the value of the legalname property.
   *
   * @return possible object is {@link String }
   */
  public String getLEGALNAME() {
    return legalname;
  }

  /**
   * Sets the value of the legalname property.
   *
   * @param value allowed object is {@link String }
   */
  public void setLEGALNAME(String value) {
    this.legalname = value;
  }

  /**
   * Gets the value of the tradingname property.
   *
   * @return possible object is {@link String }
   */
  public String getTRADINGNAME() {
    return tradingname;
  }

  /**
   * Sets the value of the tradingname property.
   *
   * @param value allowed object is {@link String }
   */
  public void setTRADINGNAME(String value) {
    this.tradingname = value;
  }

  /**
   * Gets the value of the path property.
   *
   * @return possible object is {@link String }
   */
  public String getPATH() {
    return path;
  }

  /**
   * Sets the value of the path property.
   *
   * @param value allowed object is {@link String }
   */
  public void setPATH(String value) {
    this.path = value;
  }

}