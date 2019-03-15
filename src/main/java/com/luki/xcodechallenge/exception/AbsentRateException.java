package com.luki.xcodechallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Rxchange rate is not present")
public class AbsentRateException extends RuntimeException {
    public AbsentRateException(String message) {
        super(message);
    }
}
