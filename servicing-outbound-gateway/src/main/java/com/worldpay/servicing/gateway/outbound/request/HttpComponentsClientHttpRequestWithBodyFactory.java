package com.worldpay.servicing.gateway.outbound.request;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.net.URI;

class HttpComponentsClientHttpRequestWithBodyFactory
    extends HttpComponentsClientHttpRequestFactory {

  HttpComponentsClientHttpRequestWithBodyFactory() {
    super();
  }

  HttpComponentsClientHttpRequestWithBodyFactory(HttpClient httpClient) {
    super(httpClient);
  }

  @Override
  protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
    if (httpMethod == HttpMethod.GET) {
      return new HttpGetRequestWithEntity(uri);
    }
    return super.createHttpUriRequest(httpMethod, uri);
  }

  private class HttpGetRequestWithEntity extends HttpEntityEnclosingRequestBase {

    public HttpGetRequestWithEntity(final URI uri) {
      super.setURI(uri);
    }

    @Override
    public String getMethod() {
      return HttpMethod.GET.name();
    }
  }
}
