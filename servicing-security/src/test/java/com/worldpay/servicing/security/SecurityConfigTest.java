package com.worldpay.servicing.security;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource({"classpath:config/servicing-application-test.properties"})
@ContextConfiguration(classes = {SecurityConfig.class})
public class SecurityConfigTest {

  @Autowired
  private ApplicationContext context;

  @BeforeClass
  public static void beforeClass() {
    System.setProperty("SERVICING_CONF_DIR", "./src/test/resources/config");
    System.setProperty("SDS_ENCRYPTED_DIR", "./src/test/resources/config");
  }

  @Test
  public void testContext() {
    Assert.assertNotNull("Context should not be null", context);
  }

  @Test
  public void testGetSecurityConfigBean() {
    SecurityConfig config = context.getBean(SecurityConfig.class);
    Assert.assertNotNull("SecurityConfig should not be null", config);
  }
}
