package com.worldpay.servicing.gateway.outbound.request;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class RequestBeanFactory implements FactoryBean<RequestFactoryBuilder> {

  private static final String INTERSERVICES_COMMS_ENCRYPTED = "interservices.comms.encrypted";

  @Autowired
  private Environment environment;

  public RequestBeanFactory(Environment environment) {
    this.environment = environment;
  }

  @Override
  public RequestFactoryBuilder getObject() throws Exception {
    Boolean isEncrypted = environment.containsProperty(INTERSERVICES_COMMS_ENCRYPTED)
        ? Boolean.valueOf(environment.getProperty(INTERSERVICES_COMMS_ENCRYPTED))
        : false;

    return isEncrypted ? new HttpsRequestFactoryBuilder(environment)
        : new HttpRequestFactoryBuilder();
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  @Override
  public Class<?> getObjectType() {
    return RequestFactoryBuilder.class;
  }

}
