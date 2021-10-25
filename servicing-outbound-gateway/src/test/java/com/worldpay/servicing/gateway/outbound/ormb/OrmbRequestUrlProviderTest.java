package com.worldpay.servicing.gateway.outbound.ormb;

import static com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter.BILL_PAYMENT_STATUS;
import static com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter.CURRENCY;
import static com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter.PARTY_ID;
import static com.worldpay.servicing.model.dto.ormb.BillSearchCriteria.PaymentStatus.PAID;
import static org.junit.Assert.assertEquals;

import com.worldpay.servicing.gateway.outbound.OrmbClientTestConfig;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@Import(OrmbClientTestConfig.class)
public class OrmbRequestUrlProviderTest {

  @Autowired
  private Environment env;

  @Autowired
  private OrmbRequestUrlProvider underTest;

  @BeforeClass
  public static void beforeClass() {
    System.setProperty("SERVICING_CONF_DIR", "./src/test/resources");
    System.setProperty("SDS_ENCRYPTED_DIR", "./src/test/resources");
  }

  @Test
  public void testGetRequestUrlWithNullParameters() {
    String actualUrl = underTest.getRequestUrl(null, null);
    String expectedUrl = env.getProperty("ormb.url");
    assertEquals("Expected url is different that the actual one", expectedUrl, actualUrl);
  }

  @Test
  public void testGetRequestUrlWithMissingParameters() {
    Map<OrmbRequestParameter, String> paramsMap = new LinkedHashMap<>();
    paramsMap.put(PARTY_ID, null);
    String actualUrl = underTest.getRequestUrl(null, paramsMap);
    String expectedUrl = env.getProperty("ormb.url");
    assertEquals("Expected url is different that the actual one", expectedUrl, actualUrl);
  }

  @Test
  public void testGetRequestUrl() {
    String specificPath = "specificPath";
    Map<OrmbRequestParameter, String> paramsMap = new LinkedHashMap<>();
    paramsMap.put(PARTY_ID, "PO123");
    paramsMap.put(CURRENCY, "GBP");
    paramsMap.put(BILL_PAYMENT_STATUS, PAID.name());

    String actualUrl = underTest.getRequestUrl(specificPath, paramsMap);
    String expectedUrl = env.getProperty("ormb.url") + specificPath + "?perIdNbr=PO123&currency=GBP&billPaymentStatus=PAID";

    assertEquals("Expected url is different that the actual one", expectedUrl, actualUrl);
  }

}
