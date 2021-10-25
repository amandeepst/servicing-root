package com.worldpay.servicing.gateway.outbound;

import java.io.File;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

/*
 * Class that checks an ApplicationContext can be raised
 */

public class WebServiceSecuredClientConfigNegativeTest {

  @BeforeClass
  public static void beforeClass() {
    System.setProperty("SERVICING_CONF_DIR", "./src/test/resources");
    System.setProperty("SDS_ENCRYPTED_DIR", "./src/test/resources");
  }

  @Test
  public void testContext() throws Exception {
    File f = new File(".");
    ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withInitializer(new ConditionEvaluationReportLoggingListener(LogLevel.DEBUG))
        .withPropertyValues("spring.config.location=classpath:/servicing-application-ng.properties", "spring.profiles.active=securedSOAPClient", "client.ssl.trust-store=" + f.getCanonicalPath() + "/target/test-classes/keystores/tomcatTrustStore1.jks", "client.ssl.trust-store-password=changeit")
        .withConfiguration(AutoConfigurations.of(WebServiceSecuredClientConfig.class));
    contextRunner.run((context) -> {
      Assert.assertNotNull("The context should not be null", context);
      Assert.assertNotNull("There should have been a startup failure due to inexistent truststore:", context.getStartupFailure());
    });
  }
  
  @Test
  public void testContextWithInvalidTruststorePassword() throws Exception {
    File f = new File(".");
    ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withInitializer(new ConditionEvaluationReportLoggingListener(LogLevel.DEBUG))
        .withPropertyValues("spring.config.location=classpath:/servicing-application-ng.properties", "spring.profiles.active=securedSOAPClient", "client.ssl.trust-store=" + f.getCanonicalPath() + "/target/test-classes/keystores/tomcatTrustStore1.jks", "client.ssl.trust-store-password=")
        .withConfiguration(AutoConfigurations.of(WebServiceSecuredClientConfig.class));
    contextRunner.run((context) -> {
      Assert.assertNotNull("The context should not be null", context);
      Assert.assertNotNull("There should have been a startup failure due to inexistent truststore:", context.getStartupFailure());
    });
  }
}
