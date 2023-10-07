package com.okadali.labreportsystemv2backend.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidFileTypeException extends CustomException{
    public InvalidFileTypeException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST);
    }
}
