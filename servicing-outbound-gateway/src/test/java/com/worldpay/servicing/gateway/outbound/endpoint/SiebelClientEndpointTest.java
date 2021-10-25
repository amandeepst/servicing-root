package com.worldpay.servicing.gateway.outbound.endpoint;

import static org.springframework.ws.test.client.RequestMatchers.payload;
import static org.springframework.ws.test.client.ResponseCreators.withPayload;

import com.worldpay.servicing.gateway.outbound.WebServiceClientTestConfig;
import com.worldpay.servicing.gateway.outbound.model.approveservice.WpFinancialApprovalsOutput;
import com.worldpay.servicing.gateway.outbound.model.internal.ServicingApprovalItem;
import com.worldpay.servicing.gateway.outbound.model.partyhierarchy.WpHierQueryOutput;
import com.worldpay.servicing.model.dto.CaseIdentifier;
import com.worldpay.servicing.model.dto.ServiceItemDTO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;

import java.math.BigDecimal;

import javax.xml.transform.Source;


@RunWith(SpringRunner.class)
@Import(WebServiceClientTestConfig.class)
public class SiebelClientEndpointTest {

  @Autowired
  private SiebelClientEndpoint client;

  private MockWebServiceServer mockServer;

  @BeforeClass
  public static void beforeClass() {
    System.setProperty("SERVICING_CONF_DIR", "./src/test/resources");
    System.setProperty("SDS_ENCRYPTED_DIR", "./src/test/resources");
  }

  @Before
  public void createServer() throws Exception {
    mockServer = MockWebServiceServer.createServer(client);
  }

  @Test
  public void approveItem() throws Exception {

    ServicingApprovalItem servicingAprovalItem = createServicingApprovalItem(createServiceItemDTO());

    Source expectedRequestPayload = new StringSource(
        "<ns3:wp_financial_approvals_Input xmlns:ns3=\"http://siebel.com/CustomUI\" xmlns:ns2=\"http://www.siebel.com/xml/approvalCheckRequest\" xmlns:ns4=\"http://www.siebel.com/xml/WP%20ORMB%20Party%20Response%20IO\"><ns2:approvalCheckRequest><ns2:callInfo/><ns2:approval><ns2:caseId>1-U61FQE</ns2:caseId><ns2:createdBy>CONWAYT913</ns2:createdBy><ns2:PositionName>Debt Management Queue</ns2:PositionName><ns2:approvalItem><ns2:service>ADJUSTMENT</ns2:service><ns2:serviceClass>UTI</ns2:serviceClass><ns2:serviceItemType>WAFUTI</ns2:serviceItemType><ns2:amount>200.50</ns2:amount><ns2:currency>GBP</ns2:currency></ns2:approvalItem></ns2:approval></ns2:approvalCheckRequest></ns3:wp_financial_approvals_Input>");
    Source responsePayload = new StringSource(
        "<ns:wp_financial_approvals_Output xmlns:ns=\"http://siebel.com/CustomUI\"><approvalCheckRequest xmlns=\"http://www.siebel.com/xml/approvalCheckRequest\"><callInfo/><approval><caseId>1-U61FQE</caseId><createdBy>CONWAYT913</createdBy><PositionName>Debt Management Queue</PositionName><approvalItem><service>ADJUSTMENT</service><serviceId>12345</serviceId><serviceClass>UTI</serviceClass><serviceItemType>WAFUTI</serviceItemType><amount>20</amount><currency>GBP</currency><creditDebit>Credit</creditDebit></approvalItem><approvalId>APPROVED</approvalId></approval></approvalCheckRequest></ns:wp_financial_approvals_Output>");

    mockServer.expect(payload(expectedRequestPayload)).andRespond(withPayload(responsePayload));

    // check for approval
    WpFinancialApprovalsOutput result = client.checkApproval(servicingAprovalItem);
    Assert.assertEquals("Status is not APPROVED", "APPROVED",
        result.getApprovalCheckRequest().getApproval().getApprovalId());
  }

  @Test
  public void queryPartyHierarchyOk() {
    Source expectedRequestPayload = new StringSource(
        "<ns4:wp_hier_query_Input xmlns:ns2=\"http://www.siebel.com/xml/approvalCheckRequest\" xmlns:ns3=\"http://www.siebel.com/xml/WP%20ORMB%20Party%20Response%20IO\" xmlns:ns4=\"http://siebel.com/CustomUI\"><ns4:PartyId>test</ns4:PartyId></ns4:wp_hier_query_Input>");
    Source responsePayload = new StringSource(
        "<ns:wp_hier_query_Output xmlns:ns=\"http://siebel.com/CustomUI\"><SEARCHED_PARTY xmlns=\"http://www.siebel.com/xml/WP%20ORMB%20Party%20Response%20IO\"><ERR_MSG>No record matching the search specification is found</ERR_MSG><MASTER_PARTY_ID/><ERR_CODE>(SBL-BPR-00162)--(SBL-CMI-00122)</ERR_CODE><EXTERNAL_MERCHANT_ID/><PARTY><EXTERNAL_MERCHANT_ID/><SIEBEL_ID/><LEGAL_NAME/><TRADING_NAME/><PATH/></PARTY></SEARCHED_PARTY></ns:wp_hier_query_Output>");

    mockServer.expect(payload(expectedRequestPayload)).andRespond(withPayload(responsePayload));

    // check for party hierarchy
    WpHierQueryOutput result = client.queryPartyHierarchy("test");
    Assert.assertEquals("Error code should be (SBL-BPR-00162)--(SBL-CMI-00122)", "(SBL-BPR-00162)--(SBL-CMI-00122)",
        result.getSEARCHEDPARTY().getERRCODE());
  }

  private ServicingApprovalItem createServicingApprovalItem(ServiceItemDTO sidto) {
    ServicingApprovalItem si = new ServicingApprovalItem(sidto, "CONWAYT913", "Debt Management Queue");
    si.setAmount(new BigDecimal("200.50"));
    si.setCurrency("GBP");
    si.setService("ADJUSTMENT");
    si.setServiceClass("UTI");
    si.setServiceItemType("WAFUTI");

    return si;
  }

  private ServiceItemDTO createServiceItemDTO() {
    ServiceItemDTO si = new ServiceItemDTO();
    CaseIdentifier ci = new CaseIdentifier("1-U61FQE");
    si.setCaseIdentifier(ci);

    return si;
  }

}
