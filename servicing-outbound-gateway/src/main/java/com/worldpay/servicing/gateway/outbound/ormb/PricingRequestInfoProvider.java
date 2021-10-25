package com.worldpay.servicing.gateway.outbound.ormb;

import static com.worldpay.servicing.gateway.outbound.ormb.OrmbRequestParameter.PARTY_ID;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class PricingRequestInfoProvider implements OrmbRequestInfoProvider<String> {

  @Override
  public Map<OrmbRequestParameter, String> getRequestParams(String partyId) {
    Map<OrmbRequestParameter, String> paramsMap = new LinkedHashMap<>();
    paramsMap.put(PARTY_ID, partyId);
    return paramsMap;
  }

  @Override
  public String getSpecificPath() {
    return "Ser133/getPricing/params";
  }
}
