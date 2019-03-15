package com.luki.xcodechallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Sorting Data")
public class BadSortingData extends RuntimeException {
    public BadSortingData(String message) {
        super(message);
    }
}



