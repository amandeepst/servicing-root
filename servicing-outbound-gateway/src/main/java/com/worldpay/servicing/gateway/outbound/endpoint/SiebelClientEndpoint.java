package com.worldpay.servicing.gateway.outbound.endpoint;

import com.worldpay.servicing.common.logging.LoggingMarker;
import com.worldpay.servicing.common.logging.XmlLogHelper;
import com.worldpay.servicing.gateway.outbound.model.approveservice.Approval;
import com.worldpay.servicing.gateway.outbound.model.approveservice.ApprovalCheckRequest;
import com.worldpay.servicing.gateway.outbound.model.approveservice.ApprovalItem;
import com.worldpay.servicing.gateway.outbound.model.approveservice.CallInfo;
import com.worldpay.servicing.gateway.outbound.model.approveservice.WpFinancialApprovalsInput;
import com.worldpay.servicing.gateway.outbound.model.approveservice.WpFinancialApprovalsOutput;
import com.worldpay.servicing.gateway.outbound.model.internal.ServicingApprovalItem;
import com.worldpay.servicing.gateway.outbound.model.partyhierarchy.WpHierQueryInput;
import com.worldpay.servicing.gateway.outbound.model.partyhierarchy.WpHierQueryOutput;
import com.worldpay.tpg.logging.TpgLogger;
import com.worldpay.tpg.logging.TpgLoggerFactory;
import org.javasimon.aop.Monitored;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Monitored
public class SiebelClientEndpoint extends WebServiceGatewaySupport {

  public static final String COM_WORLDPAY_SERVICING_GATEWAY_OUTBOUND_MODEL_APPROVESERVICE = "com.worldpay.servicing.gateway.outbound"
      + ".model.approveservice";
  private static final TpgLogger TPG_LOGGER = TpgLoggerFactory
      .getTpgLogger(SiebelClientEndpoint.class);
  private static final String SIEBEL_USER_PROPERTY_NAME = "siebel.approvalService.user";
  private static final String SIEBEL_USER_PASS_PROPERTY_NAME = "siebel.approvalService.password";
  private static final String SOAP_ACTION_WP_FINANCIAL_APPROVALS = "document/http://siebel.com/"
      + "CustomUI:wp_financial_approvals";
  private static final String SOAP_ACTION_WP_HIERARCHY_QUERY = "document/http://siebel.com/"
      + "CustomUI:wp_hier_query";

  private Environment env;

  public SiebelClientEndpoint(Environment env) {
    super();
    this.env = env;
  }

  public WpFinancialApprovalsOutput checkApproval(ServicingApprovalItem approvalItem) {

    ApprovalCheckRequest request = new ApprovalCheckRequest();
    request.setCallInfo(new CallInfo());
    Approval approval = new Approval();
    approval.setCaseId(approvalItem.getCaseId());
    approval.setCreatedBy(approvalItem.getUserName());
    approval.setPositionName(approvalItem.getUserPosition());
    ApprovalItem ai = convertApprovalItem(approvalItem);
    approval.getApprovalItem().add(ai);
    request.setApproval(approval);
    WpFinancialApprovalsInput input = new WpFinancialApprovalsInput();
    input.setApprovalCheckRequest(request);

    long startTime = System.currentTimeMillis();
    WpFinancialApprovalsOutput output = null;
    try {
      output = (WpFinancialApprovalsOutput) getWebServiceTemplate()
          .marshalSendAndReceive(getDefaultUri(),
              input, new HeaderHandler(new Authentication(env.getProperty(SIEBEL_USER_PROPERTY_NAME),
                  env.getProperty(SIEBEL_USER_PASS_PROPERTY_NAME)),
                  SOAP_ACTION_WP_FINANCIAL_APPROVALS));
      return output;
  } finally {
      TPG_LOGGER.info(LoggingMarker.SIEBEL_APPROVAL_INFO, "Requesting approval.... input:{} output:{} call duration: {}ms",
          XmlLogHelper.marshallObjectIntoString(COM_WORLDPAY_SERVICING_GATEWAY_OUTBOUND_MODEL_APPROVESERVICE, input),
          XmlLogHelper.marshallObjectIntoString(COM_WORLDPAY_SERVICING_GATEWAY_OUTBOUND_MODEL_APPROVESERVICE, output),
          (System.currentTimeMillis() - startTime));
    }
  }

  @Cacheable(value = "partyHierarchyCache", key = "#p0")
  public WpHierQueryOutput queryPartyHierarchy(String partyId) {
    WpHierQueryInput input = new WpHierQueryInput();
    input.setPartyId(partyId);

    TPG_LOGGER.info(LoggingMarker.SIEBEL_PARTYHIERARCHY_INFO, "Requesting party hierarchy for {}", partyId);
    long startTime = System.currentTimeMillis();
    try {
      return (WpHierQueryOutput) getWebServiceTemplate()
          .marshalSendAndReceive(getDefaultUri(),
              input, new HeaderHandler(new Authentication(env.getProperty(SIEBEL_USER_PROPERTY_NAME),
                  env.getProperty(SIEBEL_USER_PASS_PROPERTY_NAME)), SOAP_ACTION_WP_HIERARCHY_QUERY));
    } finally {
      TPG_LOGGER.debug(LoggingMarker.SIEBEL_PARTYHIERARCHY_DEBUG, "Siebel Party Hierarchy call duration: {}ms.",
          (System.currentTimeMillis() - startTime));
    }
  }

  private ApprovalItem convertApprovalItem(ServicingApprovalItem approvalItem) {
    ApprovalItem ai = new ApprovalItem();
    ai.setAmount(approvalItem.getAmount().toPlainString());
    ai.setCreditDebit(approvalItem.getCreditDebit());
    ai.setCurrency(approvalItem.getCurrency());
    ai.setService(approvalItem.getService());
    ai.setServiceClass(approvalItem.getServiceClass());
    ai.setServiceId(approvalItem.getServiceId());
    ai.setServiceItemType(approvalItem.getServiceItemType());

    return ai;
  }
}