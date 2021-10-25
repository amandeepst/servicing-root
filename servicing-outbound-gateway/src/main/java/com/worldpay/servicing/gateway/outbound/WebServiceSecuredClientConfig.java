package com.worldpay.servicing.gateway.outbound;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ResourceLoader;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import com.worldpay.servicing.common.exception.ServicingException;
import com.worldpay.servicing.common.logging.LoggingMarker;
import com.worldpay.servicing.gateway.outbound.endpoint.SiebelClientEndpoint;
import com.worldpay.tpg.logging.TpgLogger;
import com.worldpay.tpg.logging.TpgLoggerFactory;

@Configuration
@ComponentScan({"com.worldpay.servicing.gateway.outbound.endpoint",
    "com.worldpay.servicing.common"})
@Profile("securedSOAPClient")
public class WebServiceSecuredClientConfig extends AbstractWebServiceClientConfig
    implements ResourceLoaderAware {

  private static final TpgLogger TPG_LOGGER =
      TpgLoggerFactory.getTpgLogger(WebServiceSecuredClientConfig.class);

  private static final String SIEBELCLIENT_TRUSTSTORE_PROP_TEMPLATE = "file:%s";

  private static final String SIEBELCLIENT_TRUSTSTORE_PASSWORD_PROP_NAME =
      "client.ssl.trust-store-password";

  private static final String SIEBELCLIENT_TRUSTSTORE_FILE_PROP_NAME = "client.ssl.trust-store";

  private ResourceLoader resourceLoader;

  @Bean
  public SiebelClientEndpoint siebelClient(Jaxb2Marshaller marshaller) {
    SiebelClientEndpoint client = super.siebelClient(marshaller);
    client.setMessageSender(httpComponentsMessageSender());

    return client;
  }

  private HttpComponentsMessageSender httpComponentsMessageSender() {
    HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
    httpComponentsMessageSender.setHttpClient(httpClient());

    return httpComponentsMessageSender;
  }

  private HttpClient httpClient() {
    return HttpClientBuilder.create().setSSLSocketFactory(sslConnectionSocketFactory())
        .addInterceptorFirst(new HttpComponentsMessageSender.RemoveSoapHeadersInterceptor())
        .build();
  }

  private SSLConnectionSocketFactory sslConnectionSocketFactory() {
    return new SSLConnectionSocketFactory(sslContext(), new DefaultHostnameVerifier());
  }

  private SSLContext sslContext() {
    try {
      String siebelTruststorePass = env.getProperty(SIEBELCLIENT_TRUSTSTORE_PASSWORD_PROP_NAME);
      return SSLContextBuilder.create()
          .loadTrustMaterial(
              resourceLoader.getResource(String.format(SIEBELCLIENT_TRUSTSTORE_PROP_TEMPLATE,
                  env.getProperty(SIEBELCLIENT_TRUSTSTORE_FILE_PROP_NAME))).getFile(),
              StringUtils.isEmpty(siebelTruststorePass) ? null : siebelTruststorePass.toCharArray())
          .build();
    } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException
        | CertificateException | IOException e) {
      TPG_LOGGER.error(LoggingMarker.SIEBEL_ENDPOINT_TRUSTSTORE_ERROR, e.getMessage());
      throw new ServicingException(
          "The truststore to initialize the Siebel endpoint client is either invalid or does not exist on disk",
          e);
    }
  }

  @Override
  public void setResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }
}
