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

import com.worldpay.servicing.model.dto.ormb.BillSearchCriteria;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MerchantBillsRequestInfoProvider implements OrmbRequestInfoProvider<BillSearchCriteria> {

  @Override
  public Map<OrmbRequestParameter, String> getRequestParams(BillSearchCriteria searchCriteria) {
    Map<OrmbRequestParameter, String> paramsMap = new LinkedHashMap<>();
    addToMapNotNullValues(paramsMap, PARTY_ID, searchCriteria.getPartyId());
    addToMapNotNullValues(paramsMap, BILL_DATE_FROM, searchCriteria.getBillDateFrom());
    addToMapNotNullValues(paramsMap, BILL_DATE_TO, searchCriteria.getBillDateTo());
    String billPaymentStatus = searchCriteria.getPaymentStatus() != null ? searchCriteria.getPaymentStatus().name() : null;
    addToMapNotNullValues(paramsMap, BILL_PAYMENT_STATUS, billPaymentStatus);
    addToMapNotNullValues(paramsMap, LCP, searchCriteria.getLegalCounterparty());
    addToMapNotNullValues(paramsMap, CURRENCY, searchCriteria.getCurrency());
    addToMapNotNullValues(paramsMap, BILL_AMOUNT_FROM, searchCriteria.getBillAmountFrom());
    addToMapNotNullValues(paramsMap, BILL_AMOUNT_TO, searchCriteria.getBillAmountTo());
    addToMapNotNullValues(paramsMap, ALT_BILL_ID, searchCriteria.getAltBillId());

    return paramsMap;
  }

  private <T> void addToMapNotNullValues(Map<OrmbRequestParameter, String> paramsMap, OrmbRequestParameter key, T value) {
    if (value != null) {
      paramsMap.put(key, value.toString());
    }
  }

  @Override
  public String getSpecificPath() {
    return "Ser131/getMerchantOpenBills/params";
  }
}
