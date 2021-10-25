package com.worldpay.servicing.gateway.outbound;

import com.worldpay.servicing.gateway.outbound.endpoint.SiebelClientEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Class that is used by other tests as configuration entry point
 *
 * @author hartupi669
 */

@Configuration
@Import(WebServiceClientConfig.class)
@PropertySource({"file:${SDS_ENCRYPTED_DIR}/servicing-application.properties"})
public class WebServiceClientTestConfig {

  private static final String SIEBELCLIENT_PACKAGE =
      "com.worldpay.servicing.gateway.outbound.model.approveservice:com.worldpay.servicing.gateway.outbound.model.partyhierarchy";

  private static final String SIEBELCLIENT_URI_PROP_NAME = "siebel.approvalService.uri";

  @Autowired
  private Environment env;

  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath(SIEBELCLIENT_PACKAGE);
    return marshaller;
  }

  @Bean
  public SiebelClientEndpoint siebelClient(Jaxb2Marshaller marshaller) {
    SiebelClientEndpoint client = new SiebelClientEndpoint(env);
    client.setDefaultUri(env.getProperty(SIEBELCLIENT_URI_PROP_NAME));
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }

}
