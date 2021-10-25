package com.worldpay.servicing.gateway.outbound.request;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class HttpRequestFactoryBuilder implements RequestFactoryBuilder {

  @Override
  public HttpComponentsClientHttpRequestFactory buildRequestFactory() {
    return new HttpComponentsClientHttpRequestWithBodyFactory();
  }

  @Override
  public FactoryType getFactoryType() {
    return FactoryType.HTTP_FACTORY;
  }

}
