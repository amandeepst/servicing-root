package com.worldpay.servicing.gateway.outbound.request;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.worldpay.servicing.common.exception.ServicingException;
import com.worldpay.servicing.gateway.outbound.request.RequestFactoryBuilder.FactoryType;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@RunWith(MockitoJUnitRunner.class)
public class HttpsRequestFactoryBuilderTest {

  @Mock
  private Environment env;

  @InjectMocks
  private HttpsRequestFactoryBuilder underTest;

  @Test
  public void testGetFactoryType() {
    Assert.assertTrue("The factory type should be " + FactoryType.HTTPS_FACTORY + " but was " + underTest.getFactoryType(),
        underTest.getFactoryType().equals(FactoryType.HTTPS_FACTORY));
  }

  @Test
  public void testFindKeystore() {
    when(env.getProperty(eq(RequestFactoryBuilder.SERVER_KEYSTORE_PATH))).thenReturn("src/test/resources/keystores/tomcatKeyStore.jks");
    when(env.getProperty(eq(RequestFactoryBuilder.SERVER_KEYSTORE_SECRET))).thenReturn("changeit", "changeit");

    HttpComponentsClientHttpRequestFactory result = underTest.buildRequestFactory();
    Assert.assertNotNull("Http request factory should not be null", result);
    verify(env, VerificationModeFactory.times(3)).getProperty(anyString());
  }

  @Test
  public void testFindKeystoreWithDefaultProps() {
    when(env.getProperty(eq(RequestFactoryBuilder.SERVER_KEYSTORE_PATH))).thenReturn("src/test/resources/keystores/tomcatKeyStore.jks");
    when(env.getProperty(eq(RequestFactoryBuilder.SERVER_KEYSTORE_SECRET))).thenReturn("changeit", "changeit");
    when(env.containsProperty(eq(RequestFactoryBuilder.HTTP_CLIENT_CONN_TIMEOUT))).thenReturn(false);
    when(env.containsProperty(eq(RequestFactoryBuilder.HTTP_CLIENT_MAXCONN))).thenReturn(true);
    when(env.getProperty(eq(RequestFactoryBuilder.HTTP_CLIENT_MAXCONN))).thenReturn("");
    when(env.containsProperty(eq(RequestFactoryBuilder.HTTP_CLIENT_READ_TIMEOUT))).thenReturn(true);
    when(env.getProperty(eq(RequestFactoryBuilder.HTTP_CLIENT_READ_TIMEOUT))).thenReturn("10000");

    HttpComponentsClientHttpRequestFactory result = underTest.buildRequestFactory();
    Assert.assertNotNull("Http request factory should not be null", result);
    verify(env, VerificationModeFactory.times(6)).getProperty(anyString());
  }

  @Test(expected = ServicingException.class)
  public void testLoadEmptyKeystore() {
    when(env.getProperty(eq(RequestFactoryBuilder.SERVER_KEYSTORE_PATH)))
        .thenReturn("src/test/resources/keystores/tomcatKeyStoreEmpty.jks");
    when(env.getProperty(eq(RequestFactoryBuilder.SERVER_KEYSTORE_SECRET))).thenReturn("changeit", "changeit");

    HttpComponentsClientHttpRequestFactory result = underTest.buildRequestFactory();
  }

  @Test(expected = ServicingException.class)
  public void testFindKeystoreWithWrongPass() {
    when(env.getProperty(eq(RequestFactoryBuilder.SERVER_KEYSTORE_PATH))).thenReturn("src/test/resources/keystores/tomcatKeyStore.jks");
    when(env.getProperty(eq(RequestFactoryBuilder.SERVER_KEYSTORE_SECRET))).thenReturn("x", "x");

    HttpComponentsClientHttpRequestFactory result = underTest.buildRequestFactory();
  }
}
