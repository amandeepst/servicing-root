package com.worldpay.servicing.gateway.inbound;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@PropertySource({"classpath:servicing-application-test.properties"})
@Configuration
@Import({WebServiceConfig.class})
@EnableAutoConfiguration
public class WebServiceTestConfig {

}
