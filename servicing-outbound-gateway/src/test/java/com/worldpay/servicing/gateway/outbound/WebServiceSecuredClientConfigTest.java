package com.worldpay.servicing.gateway.outbound;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import com.worldpay.servicing.gateway.outbound.endpoint.SiebelClientEndpoint;

/*
 * Class that checks an ApplicationContext can be raised
 */
@RunWith(SpringRunner.class)
@Import(WebServiceSecuredClientConfig.class)
@TestPropertySource({"classpath:/servicing-application.properties"})
@ActiveProfiles(profiles = "securedSOAPClient")
public class WebServiceSecuredClientConfigTest {

  @BeforeClass
  public static void beforeClass() {
    System.setProperty("SERVICING_CONF_DIR", "./src/test/resources");
    System.setProperty("SDS_ENCRYPTED_DIR", "./src/test/resources");
  }

  @Autowired
  private ApplicationContext context;

  @Test
  public void testContext() {
    Assert.assertNotNull("Context is null", context);
  }

  @Test
  public void testSpecificBean() {
    SiebelClientEndpoint sce = context.getBean(SiebelClientEndpoint.class);
    Assert.assertNotNull("The Siebel client endpoint should not be null", sce);
    Assert.assertTrue("The httpComponentsMessageSender should exist",
        sce.getMessageSenders().length > 0);
    Assert.assertTrue(
        "The httpComponentsMessageSender should be a HttpComponentsMessageSender instance",
        sce.getMessageSenders()[0] instanceof HttpComponentsMessageSender);

  }
}
