//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 10:13:29 AM BST 
//


package com.worldpay.servicing.gateway.outbound.model.approveservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the
 * com.worldpay.servicing.gateway.outbound.model.approveservice package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation for XML content. The Java representation of XML content can consist of schema derived
 * interfaces and classes representing the binding of schema type definitions, element declarations and model groups.  Factory methods for
 * each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _ApprovalCheckRequest_QNAME = new QName("http://www.siebel.com/xml/approvalCheckRequest",
      "approvalCheckRequest");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
   * com.worldpay.servicing.gateway.outbound.model.approveservice
   */
  public ObjectFactory() {
  }

  /**
   * Create an instance of {@link WpFinancialApprovalsInput }
   */
  public WpFinancialApprovalsInput createWpFinancialApprovalsInput() {
    return new WpFinancialApprovalsInput();
  }

  /**
   * Create an instance of {@link ApprovalCheckRequest }
   */
  public ApprovalCheckRequest createApprovalCheckRequest() {
    return new ApprovalCheckRequest();
  }

  /**
   * Create an instance of {@link WpFinancialApprovalsOutput }
   */
  public WpFinancialApprovalsOutput createWpFinancialApprovalsOutput() {
    return new WpFinancialApprovalsOutput();
  }

  /**
   * Create an instance of {@link ApprovalCheckRequestTopElmt }
   */
  public ApprovalCheckRequestTopElmt createApprovalCheckRequestTopElmt() {
    return new ApprovalCheckRequestTopElmt();
  }

  /**
   * Create an instance of {@link CallInfo }
   */
  public CallInfo createCallInfo() {
    return new CallInfo();
  }

  /**
   * Create an instance of {@link Approval }
   */
  public Approval createApproval() {
    return new Approval();
  }

  /**
   * Create an instance of {@link ApprovalItem }
   */
  public ApprovalItem createApprovalItem() {
    return new ApprovalItem();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ApprovalCheckRequest }{@code >}
   *
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link ApprovalCheckRequest }{@code >}
   */
  @XmlElementDecl(namespace = "http://www.siebel.com/xml/approvalCheckRequest", name = "approvalCheckRequest")
  public JAXBElement<ApprovalCheckRequest> createApprovalCheckRequest(ApprovalCheckRequest value) {
    return new JAXBElement<ApprovalCheckRequest>(_ApprovalCheckRequest_QNAME, ApprovalCheckRequest.class, null, value);
  }

}
