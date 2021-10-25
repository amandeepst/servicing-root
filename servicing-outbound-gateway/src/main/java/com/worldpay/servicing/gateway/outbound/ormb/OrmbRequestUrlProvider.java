package com.worldpay.servicing.gateway.outbound.ormb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class OrmbRequestUrlProvider {

  @Value("${ormb.url}")
  private String baseUrl;

  public String getRequestUrl(String specificPath, Map<OrmbRequestParameter, String> paramsMap) {
    UriComponentsBuilder uriBuilder = uriComponentsBuilder(specificPath, paramsMap);
    return uriBuilder.toUriString();
  }

  private UriComponentsBuilder uriComponentsBuilder(String specificUrl, Map<OrmbRequestParameter, String> paramsMap) {
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
    return builder;
  }

}
