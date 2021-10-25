package com.worldpay.servicing.common.exception;

public class ServicingException extends RuntimeException {

  private static final long serialVersionUID = -2795774587798362787L;

  public ServicingException(String message, Throwable cause) {
    super(message, cause);
  }

  public ServicingException(Throwable cause) {
    super(cause);
  }
}
