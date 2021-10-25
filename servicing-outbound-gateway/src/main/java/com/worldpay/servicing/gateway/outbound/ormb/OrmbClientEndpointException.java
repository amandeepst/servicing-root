package com.worldpay.servicing.gateway.outbound.ormb;

public class OrmbClientEndpointException extends RuntimeException {

  public OrmbClientEndpointException(String message) {
    super(message);
  }

  public OrmbClientEndpointException(String message, Throwable cause) {
    super(message, cause);
  }
}
