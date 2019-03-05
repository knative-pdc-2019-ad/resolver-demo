package com.appdirect.demo.functions.domain.exception;

public class ResolverNotFoundException extends RuntimeException {

  public ResolverNotFoundException(String message) {
    super(message);
  }

  public ResolverNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
