package com.worldpay.servicing.gateway.outbound;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;
import com.worldpay.servicing.gateway.outbound.endpoint.SiebelClientEndpoint;

/*
 * Class that checks an ApplicationContext can be raised
 */
@RunWith(SpringRunner.class)
@Import(WebServiceClientConfig.class)
@ContextConfiguration
public class WebServiceClientConfigTest {

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
    Assert.assertTrue("The httpComponentsMessageSender should not exist",
        sce.getMessageSenders().length > 0);
    Assert.assertTrue(
        "The httpComponentsMessageSender should be a HttpUrlConnectionMessageSender instance",
        sce.getMessageSenders()[0] instanceof HttpUrlConnectionMessageSender);
  }

}
