package com.worldpay.servicing.gateway.outbound;

import com.worldpay.servicing.common.logging.LoggingMarker;
import com.worldpay.servicing.gateway.outbound.request.RequestFactoryBuilder;
import com.worldpay.tpg.logging.TpgLogger;
import com.worldpay.tpg.logging.TpgLoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

public class RequestFactory {

  private static final TpgLogger LOGGER = TpgLoggerFactory.getTpgLogger(RequestFactory.class);

  private RestTemplate restTemplate;

  @Autowired
  private RequestFactoryBuilder requestFactoryBuilder;

  @PostConstruct
  public void init() {
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
        requestFactoryBuilder.buildRequestFactory();
    LOGGER.info(LoggingMarker.SRV_138_REQUEST_INFO, "Using request factory with description {}",
        requestFactoryBuilder.getFactoryType().getDescription());
    restTemplate = new RestTemplate(clientHttpRequestFactory);
  }

  public RestTemplate getRestTemplate() {
    return restTemplate;
  }
}
