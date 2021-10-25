package com.worldpay.servicing.gateway.outbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import com.worldpay.servicing.gateway.outbound.endpoint.SiebelClientEndpoint;

abstract class AbstractWebServiceClientConfig {
  
  protected static final String SIEBELCLIENT_URI_PROP_NAME = "siebel.approvalService.uri";

  protected static final String SIEBELCLIENT_PACKAGE =
      "com.worldpay.servicing.gateway.outbound.model.approveservice:com.worldpay"
          + ".servicing.gateway.outbound.model.partyhierarchy";
  
  @Autowired
  protected Environment env;
  
  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath(SIEBELCLIENT_PACKAGE);
    return marshaller;
  }
  
  protected SiebelClientEndpoint siebelClient(Jaxb2Marshaller marshaller) {
    SiebelClientEndpoint client = new SiebelClientEndpoint(env);
    client.setDefaultUri(env.getProperty(SIEBELCLIENT_URI_PROP_NAME));
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }
}
