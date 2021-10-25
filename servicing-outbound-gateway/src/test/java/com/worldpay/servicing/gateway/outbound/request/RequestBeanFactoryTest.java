package com.worldpay.servicing.gateway.outbound.request;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

@RunWith(MockitoJUnitRunner.class)
public class RequestBeanFactoryTest {

  private static final String INTERSERVICES_COMMS_ENCRYPTED = "interservices.comms.encrypted";

  @Mock
  private Environment env;

  @InjectMocks
  private RequestBeanFactory underTest;

  @Test
  public void testReturnedType() {
    Assert.assertTrue("The returned object type should be RequestFactoryBuilder", underTest.getObjectType() == RequestFactoryBuilder.class);
  }

  @Test
  public void testIsSingleton() {
    Assert.assertTrue("The returned object should be singleton", underTest.isSingleton());
  }

  @Test
  public void testWithMissingTargetProperty() throws Exception {
    when(env.containsProperty(eq(INTERSERVICES_COMMS_ENCRYPTED))).thenReturn(false);
    RequestFactoryBuilder builder = underTest.getObject();
    Assert
        .assertTrue("The returned object type should be HttpRequestFactoryBuilder", builder.getClass() == HttpRequestFactoryBuilder.class);
  }

  @Test
  public void testWithPlainHttpConnBuilder() throws Exception {
    when(env.containsProperty(eq(INTERSERVICES_COMMS_ENCRYPTED))).thenReturn(true);
    when(env.getProperty(eq(INTERSERVICES_COMMS_ENCRYPTED))).thenReturn("false");
    RequestFactoryBuilder builder = underTest.getObject();
    Assert
        .assertTrue("The returned object type should be HttpRequestFactoryBuilder", builder.getClass() == HttpRequestFactoryBuilder.class);
  }

  @Test
  public void testWithHttpsConnBuilder() throws Exception {
    when(env.containsProperty(eq(INTERSERVICES_COMMS_ENCRYPTED))).thenReturn(true);
    when(env.getProperty(eq(INTERSERVICES_COMMS_ENCRYPTED))).thenReturn("true");
    RequestFactoryBuilder builder = underTest.getObject();
    Assert.assertTrue("The returned object type should be HttpsRequestFactoryBuilder",
        builder.getClass() == HttpsRequestFactoryBuilder.class);
  }

}
