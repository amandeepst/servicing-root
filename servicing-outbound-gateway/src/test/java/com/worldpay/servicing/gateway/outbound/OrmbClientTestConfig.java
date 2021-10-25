package com.worldpay.servicing.gateway.outbound;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(OrmbClientConfig.class)
@PropertySource({"file:${SDS_ENCRYPTED_DIR}/servicing-application.properties"})
public class OrmbClientTestConfig {

}