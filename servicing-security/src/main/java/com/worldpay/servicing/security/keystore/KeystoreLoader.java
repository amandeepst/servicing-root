package com.worldpay.servicing.security.keystore;

import java.security.KeyStore;

public interface KeystoreLoader {

  String KEYSTORE_SECRET = "keystore.secret";
  String KEYSTORE_LOCATION = "keystore.location";

  KeyStore loadKeystore();
}
