package com.worldpay.servicing.security.keystore.impl;

import com.worldpay.servicing.security.exception.ServicingSecurityException;
import com.worldpay.servicing.security.keystore.KeystoreLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Component
public class KeystoreLoaderImpl implements KeystoreLoader {

  @Autowired
  private Environment env;

  @Override
  public KeyStore loadKeystore() {
    String keystoreLocation = env.getProperty(KEYSTORE_LOCATION);
    String keystoreSecret = env.getProperty(KEYSTORE_SECRET);

    KeyStore keystore = createKeystoreInstance();

    try (InputStream readStream = new FileInputStream(
        ResourceUtils.getFile(ResourceUtils.FILE_URL_PREFIX + keystoreLocation))) {
      keystore.load(readStream, keystoreSecret.toCharArray());
    } catch (IOException e) {
      throw new ServicingSecurityException("Couldn't read keystore from disk", e);
    } catch (CertificateException | NoSuchAlgorithmException e) {
      throw new ServicingSecurityException(
          "Keystore content issue. Please review your keystore content", e);
    }

    return keystore;
  }

  private KeyStore createKeystoreInstance() {
    try {
      return KeyStore.getInstance(KeyStore.getDefaultType());
    } catch (KeyStoreException e) {
      throw new ServicingSecurityException("Could not create Keystore instance", e);
    }
  }
}
