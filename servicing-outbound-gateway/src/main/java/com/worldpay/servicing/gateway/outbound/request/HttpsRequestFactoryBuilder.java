package com.worldpay.servicing.gateway.outbound.request;

import com.worldpay.servicing.common.exception.ServicingException;
import com.worldpay.servicing.common.logging.LoggingMarker;
import com.worldpay.tpg.logging.TpgLogger;
import com.worldpay.tpg.logging.TpgLoggerFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.PrivateKeyDetails;
import org.apache.http.ssl.PrivateKeyStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

public class HttpsRequestFactoryBuilder implements RequestFactoryBuilder {

  private static final TpgLogger LOGGER =
      TpgLoggerFactory.getTpgLogger(HttpsRequestFactoryBuilder.class);

  private Environment env;

  public HttpsRequestFactoryBuilder(Environment env) {
    super();
    this.env = env;
  }

  @Override
  public HttpComponentsClientHttpRequestFactory buildRequestFactory() {
    KeyStore keyStore;
    HttpComponentsClientHttpRequestWithBodyFactory requestFactory = null;

    try {
      keyStore = KeyStore.getInstance("jks");
      FileSystemResource resource = new FileSystemResource(env.getProperty(SERVER_KEYSTORE_PATH));
      InputStream inputStream = resource.getInputStream();
      keyStore.load(inputStream, env.getProperty(SERVER_KEYSTORE_SECRET).toCharArray());
      if (isEmpty(keyStore)) {
        throw new KeyStoreException("Cannot use an empty keystore.");
      }
      SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
          new SSLContextBuilder()
              .loadTrustMaterial(null,
                  (TrustStrategy) (X509Certificate[] certificates, String authType) -> true)
              .loadKeyMaterial(keyStore, env.getProperty(SERVER_KEYSTORE_SECRET).toCharArray(),
                  (PrivateKeyStrategy) (Map<String, PrivateKeyDetails> map, Socket socket) -> env
                      .getProperty(SERVER_KEYSTORE_ALIAS))
              .build(),
          SSLConnectionSocketFactory.getDefaultHostnameVerifier());

      HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
          .setMaxConnTotal(processIntValueWithDefault(HTTP_CLIENT_MAXCONN, 5))
          .setMaxConnPerRoute(processIntValueWithDefault(HTTP_CLIENT_MAXCONN_PER_ROUTE, 5)).build();
      requestFactory = new HttpComponentsClientHttpRequestWithBodyFactory(httpClient);
      requestFactory.setReadTimeout(processIntValueWithDefault(HTTP_CLIENT_READ_TIMEOUT, 10000));
      requestFactory.setConnectTimeout(processIntValueWithDefault(HTTP_CLIENT_CONN_TIMEOUT, 10000));
    } catch (KeyStoreException e) {
      LOGGER.error(LoggingMarker.JWT_SECURITY_ERROR, "Error when trying to create keystore", e);
      throw new ServicingException("Error occured while creating HTTPS connection factory", e);
    } catch (IOException e) {
      LOGGER.error(LoggingMarker.JWT_SECURITY_KEYSTORE_LOAD_ERROR,
          "Could not load keystore from path {}. May be that path is not correct or keystore password is not correct.",
          env.getProperty(SERVER_KEYSTORE_PATH), e);
      throw new ServicingException("Error occured while creating HTTPS connection factory", e);
    } catch (NoSuchAlgorithmException | CertificateException e) {
      LOGGER.error(LoggingMarker.JWT_SECURITY_KEYSTORE_READ_ERROR, "Could not read keystore content", e);
      throw new ServicingException("Error occured while creating HTTPS connection factory", e);
    } catch (KeyManagementException | UnrecoverableKeyException e) {
      LOGGER.error(LoggingMarker.JWT_SECURITY_KEYSTORE_PROCESS_ERROR, "Keystore content cannot be processed", e);
      throw new ServicingException("Error occured while creating HTTPS connection factory", e);
    }

    return requestFactory;
  }

  private boolean isEmpty(KeyStore keyStore) throws KeyStoreException {
    return !keyStore.aliases().hasMoreElements();
  }

  @Override
  public FactoryType getFactoryType() {
    return FactoryType.HTTPS_FACTORY;
  }

  private Integer processIntValueWithDefault(String propertyName, int defaultValue) {
    if (!env.containsProperty(propertyName) || StringUtils.isEmpty(env.getProperty(propertyName))) {
      return defaultValue;
    }

    return Integer.valueOf(env.getProperty(propertyName));
  }
}
