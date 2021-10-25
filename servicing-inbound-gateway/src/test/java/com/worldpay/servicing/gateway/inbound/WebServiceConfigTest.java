package com.worldpay.servicing.gateway.inbound;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import({WebServiceTestConfig.class})
public class WebServiceConfigTest {

  @Autowired
  private ApplicationContext context;

  @BeforeClass
  public static void beforeClass() {
    System.setProperty("SERVICING_CONF_DIR", "./src/test/resources");
    System.setProperty("SDS_ENCRYPTED_DIR", "./src/test/resources");
  }

  @AfterClass
  public static void afterClass() {
    System.setProperty("SERVICING_CONF_DIR", "");
    System.setProperty("SDS_ENCRYPTED_DIR", "");
  }

  @Test
  public void testContext() {
    Assert.assertNotNull("Context is null", context);
  }
}
