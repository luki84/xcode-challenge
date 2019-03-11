package com.luki.xcodechallenge.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public class XCodeException extends RuntimeException {
    private final Code code;
    private final String message;

    public XCodeException(XCodeException.Code code) {
        super();
        this.code = code;
        this.message = null;
    }

    public XCodeException(XCodeException.Code code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    @RequiredArgsConstructor
    @Getter
    public enum Code {
        UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR),
        INVALID_REQUEST(HttpStatus.BAD_REQUEST);

        private final HttpStatus httpStatus;
    }
}


