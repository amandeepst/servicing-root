package com.worldpay.servicing.security.keystore;

import com.worldpay.sop.aip.jwt.utils.JwtIssuerKey;

import java.util.Set;

public interface JwtIssuerKeyLoader {

  Set<JwtIssuerKey> loadKeys();
}
