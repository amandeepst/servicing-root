package com.worldpay.servicing.security.keystore.impl;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.worldpay.servicing.security.exception.ServicingSecurityException;
import com.worldpay.servicing.security.keystore.KeystoreLoader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.util.Enumeration;

@RunWith(MockitoJUnitRunner.class)
public class KeystoreLoaderTest {

  @Mock
  private Environment env;

  @InjectMocks
  private KeystoreLoader underTest = new KeystoreLoaderImpl();

  @Before
  public void init() {
    initMocks(underTest);
  }

  @Test
  public void testKeystoreLoad() throws Exception {
    when(env.getProperty(eq(KeystoreLoader.KEYSTORE_LOCATION)))
        .thenReturn(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "config/keystores/client_keystore.jks").getAbsolutePath());
    when(env.getProperty(eq(KeystoreLoader.KEYSTORE_SECRET))).thenReturn("changeit");
    KeyStore store = underTest.loadKeystore();
    Assert.assertNotNull("Keystore should not be null", store);
    Enumeration<String> aliases = store.aliases();
    Assert.assertTrue("The keystore is not empty", aliases.hasMoreElements());
  }

  @Test(expected = ServicingSecurityException.class)
  public void testKeystoreLoadFail() throws Exception {
    when(env.getProperty(eq(KeystoreLoader.KEYSTORE_LOCATION))).thenReturn("/x_client_keystore.jks");
    when(env.getProperty(eq(KeystoreLoader.KEYSTORE_SECRET))).thenReturn("changeit");
    underTest.loadKeystore();
  }

  @Test
  public void testKeystoreLoadFailWithCauseInspection() throws Exception {
    when(env.getProperty(eq(KeystoreLoader.KEYSTORE_LOCATION))).thenReturn("/x_client_keystore.jks");
    when(env.getProperty(eq(KeystoreLoader.KEYSTORE_SECRET))).thenReturn("changeit");
    try {
      underTest.loadKeystore();
    } catch (ServicingSecurityException se) {
      Assert.assertTrue("Should have FileNotFoundException", se.getCause() instanceof FileNotFoundException);
    }
  }

  @Test
  public void testKeystoreLoadFailDueToWrongPassword() throws Exception {
    when(env.getProperty(eq(KeystoreLoader.KEYSTORE_LOCATION)))
        .thenReturn(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "config/keystores/client_keystore.jks").getAbsolutePath());
    when(env.getProperty(eq(KeystoreLoader.KEYSTORE_SECRET))).thenReturn("x");
    try {
      underTest.loadKeystore();
    } catch (ServicingSecurityException se) {
      Assert.assertTrue("Should have IOException but it was: " + se.getCause().getClass().getName(), se.getCause() instanceof IOException);
    }
  }

  @Test
  public void testKeystoreLoadFailDueToWrongStoreFormat() throws Exception {
    when(env.getProperty(eq(KeystoreLoader.KEYSTORE_LOCATION)))
        .thenReturn(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "config/keystores/bad_keystore.jks").getAbsolutePath());
    when(env.getProperty(eq(KeystoreLoader.KEYSTORE_SECRET))).thenReturn("x");
    try {
      underTest.loadKeystore();
    } catch (ServicingSecurityException se) {
      Assert.assertTrue("Should have IOException but it was: " + se.getCause().getClass().getName(), se.getCause() instanceof IOException);
    }
  }
}
