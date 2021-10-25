package com.worldpay.servicing.gateway.outbound.ormb;

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

import com.worldpay.servicing.model.dto.ormb.BillSearchCriteria;
import com.worldpay.servicing.model.dto.ormb.BillSearchCriteria.PaymentStatus;
import com.worldpay.servicing.model.util.GenericBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class MerchantBillsRequestInfoProviderTest {

  private static final String TEST_BILL_DATE_FROM = "01-01-2020";

  private static final String TEST_BILL_DATE_TO = "01-02-2020";

  private static final PaymentStatus TEST_BILL_PAYMENT_STATUS = PAID;

  private static final String TEST_BILL_CURRENCY = "GBP";

  private static final BigDecimal TEST_BILL_AMOUNT_FROM = TEN;

  private static final BigDecimal TEST_BILL_AMOUNT_TO = TEN;

  private static final String TEST_BILL_LCP = "Worldpay";

  private static final String TEST_BILL_ALT_BILL_ID = "100006";

  private static final String TEST_BILL_PARTY_ID = "PO123";

  @InjectMocks
  private MerchantBillsRequestInfoProvider underTest;

  @Test
  public void testGetRequestParams() {
    Map<OrmbRequestParameter, String> expectedParams = getExpectedParamsMap();
    BillSearchCriteria searchCriteria = getSearchCriteria();
    assertEquals("Expected path is not equal to the actual one", expectedParams, underTest.getRequestParams(searchCriteria));
  }

  @Test
  public void testGetRequestParamsWithNoSearchCriteria() {
    Map<OrmbRequestParameter, String> expectedParams = new LinkedHashMap<>();
    BillSearchCriteria searchCriteria = GenericBuilder.of(BillSearchCriteria::new).build();
    assertEquals("Expected path is not equal to the actual one", expectedParams, underTest.getRequestParams(searchCriteria));
  }

  private BillSearchCriteria getSearchCriteria() {
    return GenericBuilder.of(BillSearchCriteria::new)
        .with(BillSearchCriteria::setPartyId, TEST_BILL_PARTY_ID)
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

  private Map<OrmbRequestParameter, String> getExpectedParamsMap() {
    Map<OrmbRequestParameter, String> paramsMap = new LinkedHashMap<>();
    paramsMap.put(PARTY_ID, TEST_BILL_PARTY_ID);
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

  @Test
  public void testGetSpecificPath() {
    String expectedPath = "Ser131/getMerchantOpenBills/params";
    assertEquals("Expected path is not equal to the actual one", expectedPath, underTest.getSpecificPath());
  }

}
