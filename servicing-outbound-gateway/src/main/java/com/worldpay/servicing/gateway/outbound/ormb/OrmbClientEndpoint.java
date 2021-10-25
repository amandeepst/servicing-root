package com.worldpay.servicing.gateway.outbound.ormb;

import static com.worldpay.servicing.common.logging.LoggingMarker.SRV_130_BALANCE_INFO;
import static com.worldpay.servicing.common.logging.LoggingMarker.SRV_131_OPENBILL_INFO;
import static com.worldpay.servicing.common.logging.LoggingMarker.SRV_132_MERCHANT_SETTLEMENT_GROUP_INFO;
import static com.worldpay.servicing.common.logging.LoggingMarker.SRV_133_PRICING_INFO;
import static com.worldpay.servicing.common.logging.LoggingMarker.SRV_ORMB_DEBUG;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.worldpay.servicing.common.context.UserInfoStore;
import com.worldpay.servicing.gateway.outbound.RequestFactory;
import com.worldpay.servicing.gateway.outbound.model.PartyChildren;
import com.worldpay.servicing.model.dto.ormb.BillSearchCriteria;
import com.worldpay.servicing.model.dto.ormb.MerchantBalanceDTO;
import com.worldpay.servicing.model.dto.ormb.OpenBillsDto;
import com.worldpay.servicing.model.dto.ormb.PriceDto;
import com.worldpay.servicing.model.dto.ormb.SettlementGroupDto;
import com.worldpay.tpg.common.annotation.Annotation;
import com.worldpay.tpg.logging.TpgLogger;
import com.worldpay.tpg.logging.TpgLoggerFactory;

import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Monitored
public class OrmbClientEndpoint {

  private static final TpgLogger LOGGER = TpgLoggerFactory.getTpgLogger(OrmbClientEndpoint.class);

  private static final String JWT_HEADER_NAME = "X-WP-Jwt";

  private static final String CORRELATION_ID_HEADER = "X-WP-Diagnostic-CorrelationId";

  @Autowired
  private RequestFactory requestFactory;

  @Autowired
  private OrmbRequestUrlProvider urlProvider;

  @Autowired
  private MerchantBillsRequestInfoProvider merchantBillsRequestInfoProvider;

  @Autowired
  private MerchantBalanceRequestInfoProvider merchantBalanceRequestInfoProvider;

  @Autowired
  private MerchantSettlementGroupRequestInfoProvider merchantSettlementGroupRequestInfoProvider;

  @Autowired
  private PricingRequestInfoProvider pricingRequestInfoProvider;

  @Autowired
  private UserInfoStore userInfoStore;

  public MerchantBalanceDTO getMerchantBalance(String partyId) {
    return invokeGetRequest(partyId, null, merchantBalanceRequestInfoProvider, MerchantBalanceDTO.class, SRV_130_BALANCE_INFO);
  }

  public OpenBillsDto getMerchantBills(BillSearchCriteria searchCriteria, PartyChildren partyChildren) {
    return invokeGetRequest(searchCriteria, partyChildren, merchantBillsRequestInfoProvider, OpenBillsDto.class, SRV_131_OPENBILL_INFO);
  }

  public SettlementGroupDto getMerchantSettlementGroup(String partyId) {
    SettlementGroupDto settlementGroupDto = invokeGetRequest(partyId, null, merchantSettlementGroupRequestInfoProvider,
        SettlementGroupDto.class,
        SRV_132_MERCHANT_SETTLEMENT_GROUP_INFO);
    return settlementGroupDto;
  }

  public PriceDto getPricing(String partyId) {
    return invokeGetRequest(partyId, null, pricingRequestInfoProvider, PriceDto.class, SRV_133_PRICING_INFO);
  }


  public <O> O invokeGetRequest(Object input, Object body, OrmbRequestInfoProvider requestInfoProvider, Class<O> outputClass,
      Annotation msgLogger) {
    RestTemplate restTemplate = requestFactory.getRestTemplate();
    HttpHeaders headers = getHttpHeaders();
    HttpEntity request = body != null ? new HttpEntity(body, headers) : new HttpEntity(headers);

    LOGGER.info(msgLogger, "Requesting ORMB call {} using GET HTTP Method for input: {}", requestInfoProvider.getSpecificPath(), input);
    String url = urlProvider
        .getRequestUrl(requestInfoProvider.getSpecificPath(), requestInfoProvider.getRequestParams(input));
    long startTime = System.currentTimeMillis();
    try {
      return restTemplate.exchange(url, HttpMethod.GET, request, outputClass).getBody();
    } catch (Exception ex) {
      throw new OrmbClientEndpointException("OrmbClientEndpointException thrown with message " + ex.getMessage(), ex);
    } finally {
      LOGGER.debug(SRV_ORMB_DEBUG, "ORMB Api call duration: {}ms.", (System.currentTimeMillis() - startTime));
    }
  }

  private HttpHeaders getHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(APPLICATION_JSON);
    headers.set(JWT_HEADER_NAME, this.userInfoStore.getJwtName());
    headers.set(CORRELATION_ID_HEADER, this.userInfoStore.getCorrelationId());
    return headers;
  }


}
