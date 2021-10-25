package com.worldpay.servicing.security.exception;

public class ServicingSecurityException extends RuntimeException {

  private static final long serialVersionUID = -4918628719952554604L;

  public ServicingSecurityException(String message, Throwable e) {
    super(message, e);
  }
}
