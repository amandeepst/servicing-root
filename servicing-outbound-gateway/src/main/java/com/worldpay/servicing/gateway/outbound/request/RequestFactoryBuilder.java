package com.worldpay.servicing.gateway.outbound.request;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public interface RequestFactoryBuilder {

  String SERVER_KEYSTORE_PATH = "server.keystore.path";

  String SERVER_KEYSTORE_SECRET = "server.keystore.secret";

  String SERVER_KEYSTORE_ALIAS = "server.keystore.clientAlias";

  String HTTP_CLIENT_MAXCONN = "http.client.maxConn";

  String HTTP_CLIENT_MAXCONN_PER_ROUTE = "http.client.maxConnPerRoute";

  String HTTP_CLIENT_READ_TIMEOUT = "http.client.readTimeout";

  String HTTP_CLIENT_CONN_TIMEOUT = "http.client.connectTimeout";

  /**
   * Creates a http request factory to be use by servicing-web to connect to ormb-web.
   *
   * @return the http request factory implementation
   */
  HttpComponentsClientHttpRequestFactory buildRequestFactory();

  /**
   * Returns the type of request factory being constructed
   *
   * @return the request factory type
   */
  FactoryType getFactoryType();

  enum FactoryType {
    HTTPS_FACTORY("HTTPS connection factory"), HTTP_FACTORY("Plain HTTP connection factory");

    private String decription;

    private FactoryType(String decription) {
      this.decription = decription;
    }

    public String getDescription() {
      return this.decription;
    }

  }
}
