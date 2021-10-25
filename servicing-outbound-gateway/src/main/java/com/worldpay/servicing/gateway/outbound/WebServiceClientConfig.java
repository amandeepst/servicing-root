package com.worldpay.servicing.gateway.outbound;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import com.worldpay.servicing.gateway.outbound.endpoint.SiebelClientEndpoint;

@Configuration
@ComponentScan({
    "com.worldpay.servicing.gateway.outbound.endpoint",
    "com.worldpay.servicing.common"
})
@Profile("!securedSOAPClient")
public class WebServiceClientConfig extends AbstractWebServiceClientConfig {

  @Bean
  public SiebelClientEndpoint siebelClient(Jaxb2Marshaller marshaller) {
    return super.siebelClient(marshaller);
  }
}
