package com.worldpay.servicing.security.keystore.impl;

import com.worldpay.servicing.common.logging.LoggingMarker;
import com.worldpay.servicing.security.exception.ServicingSecurityException;
import com.worldpay.servicing.security.keystore.JwtIssuerKeyLoader;
import com.worldpay.servicing.security.keystore.KeystoreLoader;
import com.worldpay.sop.aip.jwt.utils.JwtIssuerKey;
import com.worldpay.tpg.logging.TpgLogger;
import com.worldpay.tpg.logging.TpgLoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.interfaces.RSAKey;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtIssuerKeyLoaderImpl implements JwtIssuerKeyLoader {

  private static final TpgLogger LOGGER = TpgLoggerFactory.getTpgLogger(JwtIssuerKeyLoaderImpl.class);

  @Autowired
  private KeystoreLoader keystoreLoader;

  @Override
  public Set<JwtIssuerKey> loadKeys() {
    Set<JwtIssuerKey> keys = new HashSet<>();
    KeyStore keystore = keystoreLoader.loadKeystore();
    try {
      Enumeration<String> aliases = keystore.aliases();
      while (aliases.hasMoreElements()) {
        String keyId = aliases.nextElement();
        keys.add(new JwtIssuerKey(keyId, (RSAKey) keystore.getCertificate(keyId).getPublicKey()));
      }
    } catch (KeyStoreException e) {
      throw new ServicingSecurityException("Error when trying to read keystore", e);
    }

    LOGGER.info(LoggingMarker.JWT_SECURITY_INFO, "Client keystore successfully read.");
    return keys;
  }

}
