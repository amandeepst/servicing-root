package com.worldpay.servicing.gateway.outbound.endpoint;

import static com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter.ALT_BILL_ID;
import static com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter.BILL_AMOUNT_FROM;
import static com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter.BILL_AMOUNT_TO;
import static com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter.BILL_DATE_FROM;
import static com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter.BILL_DATE_TO;
import static com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter.BILL_PAYMENT_STATUS;
import static com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter.CURRENCY;
import static com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter.LCP;
import static com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter.PARTY_ID;
import static com.worldpay.servicing.model.dto.ormb.BillSearchCriteria.PaymentStatus.PAID;
import static java.math.BigDecimal.TEN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldpay.servicing.gateway.outbound.OrmbClientTestConfig;
import com.worldpay.servicing.gateway.outbound.RequestFactory;
import com.worldpay.servicing.gateway.outbound.model.PartyChildren;
import com.worldpay.servicing.gateway.outbound.ormb.MerchantBalanceRequestInfoProvider;
import com.worldpay.servicing.gateway.outbound.ormb.MerchantBillsRequestInfoProvider;
import com.worldpay.servicing.gateway.outbound.ormb.MerchantSettlementGroupRequestInfoProvider;
import com.worldpay.servicing.gateway.outbound.ormb.OrmbClientEndpoint;
import com.worldpay.servicing.gateway.outbound.ormb.OrmbClientEndpointException;
import com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter;
import com.worldpay.servicing.gateway.outbound.ormb.PricingRequestInfoProvider;
import com.worldpay.servicing.gateway.outbound.utils.TestUtils;
import com.worldpay.servicing.model.dto.ormb.Balance;
import com.worldpay.servicing.model.dto.ormb.BalanceItem;
import com.worldpay.servicing.model.dto.ormb.BillSearchCriteria;
import com.worldpay.servicing.model.dto.ormb.BillSearchCriteria.PaymentStatus;
import com.worldpay.servicing.model.dto.ormb.MerchantBalanceDTO;
import com.worldpay.servicing.model.dto.ormb.OpenBills;
import com.worldpay.servicing.model.dto.ormb.OpenBillsDto;
import com.worldpay.servicing.model.dto.ormb.Price;
import com.worldpay.servicing.model.dto.ormb.PriceDto;
import com.worldpay.servicing.model.dto.ormb.SettlementGroup;
import com.worldpay.servicing.model.dto.ormb.SettlementGroupDto;
import com.worldpay.servicing.model.dto.ormb.SettlementGroupList;
import com.worldpay.servicing.model.util.GenericBuilder;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@Import(OrmbClientTestConfig.class)
public class OrmbClientEndpointTest {

  private static final String TEST_PARTY_ID = "PO123";

  private static final String TEST_BILL_DATE_FROM = "01-01-2020";

  private static final String TEST_BILL_DATE_TO = "01-02-2020";

  private static final PaymentStatus TEST_BILL_PAYMENT_STATUS = PAID;

  private static final String TEST_BILL_CURRENCY = "GBP";

  private static final BigDecimal TEST_BILL_AMOUNT_FROM = TEN;

  private static final BigDecimal TEST_BILL_AMOUNT_TO = TEN;

  private static final String TEST_BILL_LCP = "Worldpay";

  private static final String TEST_BILL_ALT_BILL_ID = "100006";

  private static final String MERCHANT_BALANCES_DIFFER = "Merchant balances are different.";

  private static final String OPEN_BILLS_ARE_DIFFER = "Open Bills are different.";

  private static final String DIFFERENT_OBJECTS_MSG = "Objects are different.";

  private static final String GET_MERCHANT_BALANCE_URL =
      "Ser130/getMerchantBalance/params?perIdNbr=";

  private static final String GET_MERCHANT_SETTLEMENT_GROUP_URL =
      "Ser132/getMerchantSettlementGroup/params?perIdNbr=";

  private static final String GET_MERCHANT_OPEN_BILLS =
      "Ser131/getMerchantOpenBills/params";

  private static final String GET_PRICING_URL =
      "Ser133/getPricing/params?perIdNbr=";

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Autowired
  Environment env;

  @Autowired
  private RequestFactory requestFactory;

  @Autowired
  private MerchantBillsRequestInfoProvider merchantBillsRequestInfoProvider;

  @Autowired
  private MerchantBalanceRequestInfoProvider merchantBalanceRequestInfoProvider;

  @Autowired
  private MerchantSettlementGroupRequestInfoProvider merchantSettlementGroupRequestInfoProvider;

  @Autowired
  private PricingRequestInfoProvider pricingRequestInfoProvider;

  @Autowired
  private OrmbClientEndpoint ormbClientEndpoint;

  private RestTemplate restTemplate;

  private MockRestServiceServer mockRestServiceServer;

  private ObjectMapper mapper = new ObjectMapper();

  @BeforeClass
  public static void beforeClass() {
    System.setProperty("SERVICING_CONF_DIR", "./src/test/resources");
    System.setProperty("SDS_ENCRYPTED_DIR", "./src/test/resources");
  }

  @Before
  public void init() {
    this.restTemplate = requestFactory.getRestTemplate();
    mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
  }


  @Test
  public void testGetMerchantBalance() throws JsonProcessingException {

    MerchantBalanceDTO merchantBalanceDTO = new MerchantBalanceDTO();
    Balance balance = getBalance();
    merchantBalanceDTO.setBalance(balance);

    mockRestServiceServer
        .expect(
            ExpectedCount.once(),
            requestTo(env.getProperty("ormb.url") + GET_MERCHANT_BALANCE_URL + TEST_PARTY_ID))
        .andExpect(method(HttpMethod.GET))
        .andRespond(
            withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(merchantBalanceDTO)));

    MerchantBalanceDTO response = ormbClientEndpoint.getMerchantBalance(TEST_PARTY_ID);
    mockRestServiceServer.verify();
    assertEquals(
        MERCHANT_BALANCES_DIFFER,
        merchantBalanceDTO.getBalance().getBalanceList().size(),
        response.getBalance().getBalanceList().size());
    assertEquals(
        MERCHANT_BALANCES_DIFFER,
        merchantBalanceDTO.getBalance().getBalanceList().get(0).getSubAccountDescription(),
        response.getBalance().getBalanceList().get(0).getSubAccountDescription());
    assertEquals(
        MERCHANT_BALANCES_DIFFER,
        merchantBalanceDTO.getBalance().getBalanceList().get(1).getSubAccountDescription(),
        response.getBalance().getBalanceList().get(1).getSubAccountDescription());
  }


  private Balance getBalance() {
    Balance balance = new Balance();
    balance.setPartyId(TEST_PARTY_ID);

    BalanceItem balanceItem1 = TestUtils.createBalanceItem(
        "PO4000383623",
        "PO1100000001",
        "00001",
        "Worldpay (UK) Limited",
        "GBP",
        "CHRG",
        String.valueOf(-1.48),
        "RECR",
        "Recurring Charges",
        String.valueOf(0),
        "2018-12-18T14:37:19.000+0000");

    BalanceItem balanceItem2 = TestUtils.createBalanceItem(
        "PO4000383623",
        "PO1100000001",
        "00001",
        "Worldpay (UK) Limited",
        "EUR",
        "FUND",
        String.valueOf(0),
        "FUND",
        "Funding",
        String.valueOf(0),
        "2018-12-18T14:37:19.000+0000");
    List<BalanceItem> balanceItems = Arrays.asList(balanceItem1, balanceItem2);
    balance.setBalanceList(balanceItems);
    return balance;
  }

  @Test
  public void testGetOpenBillsBetweenBillDates() throws JsonProcessingException {
    BillSearchCriteria searchCriteria = getSearchCriteria();

    OpenBills bills = new OpenBills();
    bills.setBillList(TestUtils.createOpenBills());
    OpenBillsDto openBillsDto = new OpenBillsDto();
    openBillsDto.setCallInfo(TestUtils.createCallInfo("test"));
    openBillsDto.setOpenBills(bills);
    PartyChildren partyChildren = TestUtils.createPartyChildren();

    String expectedUrl = getExpectedGetBillRequestPath(env.getProperty("ormb.url"), GET_MERCHANT_OPEN_BILLS,
        getExpectedGetBillsParamsMap());
    mockRestServiceServer
        .expect(ExpectedCount.once(), requestTo(expectedUrl))
        .andExpect(method(HttpMethod.GET))
        .andExpect(MockRestRequestMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockRestRequestMatchers.content().string(mapper.writeValueAsString(partyChildren)))
        .andRespond(withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(openBillsDto)));

    OpenBillsDto response = ormbClientEndpoint.getMerchantBills(searchCriteria, partyChildren);

    mockRestServiceServer.verify();
    int actualSize = openBillsDto.getOpenBills().getBillList().size();
    int expectedSize = response.getOpenBills().getBillList().size();
    String actualElement = openBillsDto.getOpenBills().getBillList().get(0).getBillid();
    String expectedElement = response.getOpenBills().getBillList().get(0).getBillid();
    assertEquals(OPEN_BILLS_ARE_DIFFER, expectedSize, actualSize);
    assertEquals(OPEN_BILLS_ARE_DIFFER, expectedElement, actualElement);
    actualElement = openBillsDto.getOpenBills().getBillList().get(1).getBillid();
    expectedElement = response.getOpenBills().getBillList().get(1).getBillid();
    assertEquals(OPEN_BILLS_ARE_DIFFER, expectedElement, actualElement);

    assertNotNull("The response should not be empty", bills);
  }

  private BillSearchCriteria getSearchCriteria() {
    return GenericBuilder.of(BillSearchCriteria::new)
        .with(BillSearchCriteria::setPartyId, TEST_PARTY_ID)
        .with(BillSearchCriteria::setBillDateFrom, TEST_BILL_DATE_FROM)
        .with(BillSearchCriteria::setBillDateTo, TEST_BILL_DATE_TO)
        .with(BillSearchCriteria::setPaymentStatus, TEST_BILL_PAYMENT_STATUS)
        .with(BillSearchCriteria::setBillAmountFrom, TEST_BILL_AMOUNT_FROM)
        .with(BillSearchCriteria::setBillAmountTo, TEST_BILL_AMOUNT_TO)
        .with(BillSearchCriteria::setCurrency, TEST_BILL_CURRENCY)
        .with(BillSearchCriteria::setLegalCounterparty, TEST_BILL_LCP)
        .with(BillSearchCriteria::setAltBillId, TEST_BILL_ALT_BILL_ID)
        .build();
  }

  private Map<OrmbRequestParameter, String> getExpectedGetBillsParamsMap() {
    Map<OrmbRequestParameter, String> paramsMap = new LinkedHashMap<>();
    paramsMap.put(PARTY_ID, TEST_PARTY_ID);
    paramsMap.put(BILL_DATE_FROM, TEST_BILL_DATE_FROM);
    paramsMap.put(BILL_DATE_TO, TEST_BILL_DATE_TO);
    paramsMap.put(BILL_PAYMENT_STATUS, TEST_BILL_PAYMENT_STATUS.name());
    paramsMap.put(LCP, TEST_BILL_LCP);
    paramsMap.put(CURRENCY, TEST_BILL_CURRENCY);
    paramsMap.put(BILL_AMOUNT_FROM, TEST_BILL_AMOUNT_FROM.toString());
    paramsMap.put(BILL_AMOUNT_TO, TEST_BILL_AMOUNT_TO.toString());
    paramsMap.put(ALT_BILL_ID, TEST_BILL_ALT_BILL_ID);
    return paramsMap;
  }

  private String getExpectedGetBillRequestPath(String baseUrl, String specificUrl, Map<OrmbRequestParameter, String> paramsMap) {
    StringBuilder url = new StringBuilder(baseUrl);
    if (specificUrl != null) {
      url.append(specificUrl);
    }
    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl(url.toString());
    if (paramsMap != null) {
      for (Map.Entry<OrmbRequestParameter, String> paramPair : paramsMap.entrySet()) {
        if (!StringUtils.isEmpty(paramPair.getValue())) {
          builder.queryParam(paramPair.getKey().getExpectedParamName(), paramPair.getValue());
        }
      }
    }
    return builder.toUriString();
  }

  @Test
  public void testGetMerchantSettlementGroupByPartyId() throws JsonProcessingException {

    SettlementGroup settlementGroup = new SettlementGroup();
    SettlementGroupList settlementGroups = new SettlementGroupList(TEST_PARTY_ID, Arrays.asList(settlementGroup));
    SettlementGroupDto settlementGroupDto = new SettlementGroupDto(settlementGroups);
    mockRestServiceServer
        .expect(
            ExpectedCount.once(),
            requestTo(env.getProperty("ormb.url") + GET_MERCHANT_SETTLEMENT_GROUP_URL + TEST_PARTY_ID))
        .andExpect(method(HttpMethod.GET))
        .andRespond(
            withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(settlementGroupDto)));

    SettlementGroupDto response = ormbClientEndpoint.getMerchantSettlementGroup(TEST_PARTY_ID);
    mockRestServiceServer.verify();

    assertEquals(
        MERCHANT_BALANCES_DIFFER,
        settlementGroupDto.getSettlementGroupList().getSettlementGroup().size(),
        response.getSettlementGroupList().getSettlementGroup().size());
  }

  @Test
  public void testGetPricingDataByPartyId() throws Exception {
    Price price = new Price();
    PriceDto priceDto = new PriceDto(Arrays.asList(price));
    mockRestServiceServer
        .expect(
            ExpectedCount.once(),
            requestTo(env.getProperty("ormb.url") + GET_PRICING_URL + TEST_PARTY_ID))
        .andExpect(method(HttpMethod.GET))
        .andRespond(
            withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(priceDto)));
    PriceDto response = ormbClientEndpoint.getPricing(TEST_PARTY_ID);
    mockRestServiceServer.verify();

    assertEquals(
        DIFFERENT_OBJECTS_MSG,
        priceDto.getPriceList().size(),
        response.getPriceList().size());
  }

  @Test
  public void testGetPricingDataByPartyIdWhenOrmbCallThrowsException() throws Exception {
    HttpStatus expectedStatus = NOT_FOUND;

    expectedException.expect(OrmbClientEndpointException.class);
    expectedException.expectMessage("OrmbClientEndpointException thrown with message " + expectedStatus);

    mockRestServiceServer
        .expect(
            ExpectedCount.once(),
            requestTo(env.getProperty("ormb.url") + GET_PRICING_URL + TEST_PARTY_ID))
        .andExpect(method(HttpMethod.GET))
        .andRespond((response) -> {
          throw new HttpClientErrorException(expectedStatus);
        });

    ormbClientEndpoint.getPricing(TEST_PARTY_ID);
    mockRestServiceServer.verify();
  }

}
