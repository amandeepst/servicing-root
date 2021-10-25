package com.worldpay.servicing.security.keystore.impl;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.worldpay.servicing.security.exception.ServicingSecurityException;
import com.worldpay.servicing.security.keystore.JwtIssuerKeyLoader;
import com.worldpay.servicing.security.keystore.KeystoreLoader;
import com.worldpay.sop.aip.jwt.utils.JwtIssuerKey;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class JwtIssuerKeyLoaderTest {

  @Mock
  private KeystoreLoader keystoreLoader;

  private KeyStore keyStore;

  @InjectMocks
  private JwtIssuerKeyLoader underTest = new JwtIssuerKeyLoaderImpl();

  @Before
  public void init() throws Exception {
    initMocks(underTest);
    keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
    keyStore.load(
        new FileInputStream(ResourceUtils.getFile(ResourceUtils.FILE_URL_PREFIX + ResourceUtils
            .getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "config/keystores/client_keystore.jks")
            .getAbsolutePath())),
        "changeit".toCharArray());
  }

  @Test
  public void testGetKeysOk() {
    when(keystoreLoader.loadKeystore()).thenReturn(keyStore);
    Set<JwtIssuerKey> keys = underTest.loadKeys();
    Assert.assertTrue("There are keys in the store", keys.size() > 0);
  }

  @Test(expected = ServicingSecurityException.class)
  public void testGetKeysNotOk() throws Exception {
    when(keystoreLoader.loadKeystore()).thenReturn(KeyStore.getInstance(KeyStore.getDefaultType()));
    underTest.loadKeys();
  }

  @Test
  public void testGetKeysNotOkAndInspectCause() throws Exception {
    when(keystoreLoader.loadKeystore()).thenReturn(KeyStore.getInstance(KeyStore.getDefaultType()));
    try {
      underTest.loadKeys();
    } catch (ServicingSecurityException se) {
      Assert.assertTrue("Should have KeyStoreException but it was: " + se.getCause().getClass().getName(),
          se.getCause() instanceof KeyStoreException);
    }

  }
}
