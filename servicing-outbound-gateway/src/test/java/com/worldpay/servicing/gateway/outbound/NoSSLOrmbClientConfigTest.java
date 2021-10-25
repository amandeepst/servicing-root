package com.worldpay.servicing.gateway.outbound;

import com.worldpay.servicing.gateway.outbound.request.HttpRequestFactoryBuilder;
import com.worldpay.servicing.gateway.outbound.request.RequestFactoryBuilder;

import org.junit.Assert;
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
@TestPropertySource({"classpath:/servicing-application-nossl.properties"})
@ContextConfiguration(classes = {OrmbClientConfig.class})
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class NoSSLOrmbClientConfigTest {

  @Autowired
  private ApplicationContext context;

  @Test
  public void testContextNoSSL() {
    Assert.assertNotNull("Context should not be null", context);
  }

  @Test
  public void testGetOrmbClientConfigBeanNoSSL() {
    OrmbClientConfig config = context.getBean(OrmbClientConfig.class);
    Assert.assertNotNull("OrmbClientConfig should not be null", config);
  }

  @Test
  public void testGetRequestFactoryBuilderBeanNoSSL() {
    RequestFactoryBuilder requestFactoryBuilder = context.getBean(RequestFactoryBuilder.class);
    Assert.assertNotNull("RequestFactoryBuilder should not be null", requestFactoryBuilder);
    Assert.assertTrue(
        "The RequestFactoryBuilder should be instance of HttpRequestFactoryBuilder.class but it was: " + requestFactoryBuilder.getClass(),
        requestFactoryBuilder instanceof HttpRequestFactoryBuilder);
  }

  @Test
  public void testGetRequestFactoryBeanNoSSL() {
    RequestFactory requestFactory = context.getBean(RequestFactory.class);
    Assert.assertNotNull("RequestFactory should not be null", requestFactory);
  }

  @Test
  public void testRestTemplateNoSSL() throws URISyntaxException, IOException {
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
