package com.okadali.labreportsystemv2backend.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{

    HttpStatus status;

    CustomException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
