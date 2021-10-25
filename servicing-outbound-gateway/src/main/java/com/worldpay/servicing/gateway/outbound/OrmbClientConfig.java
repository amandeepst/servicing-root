package com.worldpay.servicing.gateway.outbound;

import com.worldpay.servicing.gateway.outbound.request.RequestBeanFactory;
import com.worldpay.servicing.gateway.outbound.request.RequestFactoryBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan({"com.worldpay.servicing.gateway.outbound.ormb", "com.worldpay.servicing.common"})
public class OrmbClientConfig {

  @Bean
  public RequestFactory getRequestFactory() {
    return new RequestFactory();
  }

  @Bean
  public RequestFactoryBuilder getHttpsRequestFactoryBuilder(Environment environment) throws Exception {
    return new RequestBeanFactory(environment).getObject();
  }
}
