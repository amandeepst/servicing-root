package com.worldpay.servicing.gateway.outbound.ormb;

import static com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter.PARTY_ID;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class PricingRequestInfoProviderTest {

  @InjectMocks
  private PricingRequestInfoProvider underTest;

  @Test
  public void testGetRequestParams() {
    String partyId = "PO123";
    Map<OrmbRequestParameter, String> expectedParams = new HashMap<>();
    expectedParams.put(PARTY_ID, partyId);
    assertEquals("Expected path is not equal to the actual one", expectedParams, underTest.getRequestParams(partyId));
  }

  @Test
  public void testGetSpecificPath() {
    String expectedPath = "Ser133/getPricing/params";
    assertEquals("Expected path is not equal to the actual one", expectedPath, underTest.getSpecificPath());
  }

}
