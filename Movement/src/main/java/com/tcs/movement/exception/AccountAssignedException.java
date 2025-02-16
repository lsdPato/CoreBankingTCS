package com.tcs.movement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AccountAssignedException extends RuntimeException {
    public AccountAssignedException(String message) {
        super(message);
    }
}
