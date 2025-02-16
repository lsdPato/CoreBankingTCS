package com.tcs.movement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
public class NotEnoughFundsException extends RuntimeException {
  public NotEnoughFundsException(String message) {
    super(message);
  }
}

