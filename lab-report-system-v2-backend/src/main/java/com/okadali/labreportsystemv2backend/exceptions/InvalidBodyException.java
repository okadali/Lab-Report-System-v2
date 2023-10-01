package com.okadali.labreportsystemv2backend.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidBodyException extends CustomException{


    public InvalidBodyException(String msg) {
        super(msg,
                HttpStatus.BAD_REQUEST);
    }
}
