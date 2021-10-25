package com.worldpay.servicing.gateway.outbound;

import com.worldpay.servicing.gateway.outbound.request.HttpsRequestFactoryBuilder;
import com.worldpay.servicing.gateway.outbound.request.RequestFactoryBuilder;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@TestPropertySource({"classpath:/servicing-application.properties"})
@ContextConfiguration(classes = {OrmbClientConfig.class})
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class OrmbClientConfigTest {

  @Autowired
  private ApplicationContext context;

  @BeforeClass
  public static void beforeClass() {
    System.setProperty("SERVICING_CONF_DIR", "./src/test/resources");
    System.setProperty("SDS_ENCRYPTED_DIR", "./src/test/resources");
  }

  @Test
  public void testContext() {
    Assert.assertNotNull("Context should not be null", context);
  }

  @Test
  public void testGetOrmbClientConfigBean() {
    OrmbClientConfig config = context.getBean(OrmbClientConfig.class);
    Assert.assertNotNull("OrmbClientConfig should not be null", config);
  }

  @Test
  public void testGetRequestFactoryBuilderBean() {
    RequestFactoryBuilder requestFactoryBuilder = context.getBean(RequestFactoryBuilder.class);
    Assert.assertNotNull("RequestFactoryBuilder should not be null", requestFactoryBuilder);
    Assert.assertTrue("The RequestFactoryBuilder should be instance of HttpsRequestFactoryBuilder.class",
        requestFactoryBuilder instanceof HttpsRequestFactoryBuilder);
  }

  @Test
  public void testGetRequestFactoryBean() {
    RequestFactory requestFactory = context.getBean(RequestFactory.class);
    Assert.assertNotNull("RequestFactory should not be null", requestFactory);
  }

  @Test
  public void testRestTemplate() throws URISyntaxException, IOException {
    RestTemplate restTemplate = context.getBean(RequestFactory.class).getRestTemplate();
    Assert.assertNotNull("RestTemplate should not be null", restTemplate);
    Assert.assertNotNull("RequestFactory should not be null", restTemplate.getRequestFactory());
    Assert.assertEquals(
        "Method is not GET",
        HttpMethod.GET,
        restTemplate.getRequestFactory().createRequest(new URI("/"), HttpMethod.GET).getMethod());
    Assert.assertEquals(
        "Method is not POST",
        HttpMethod.POST,
        restTemplate.getRequestFactory().createRequest(new URI("/"), HttpMethod.POST).getMethod());
  }
}
