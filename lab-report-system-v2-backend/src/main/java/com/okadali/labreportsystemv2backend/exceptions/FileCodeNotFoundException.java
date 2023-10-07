package com.okadali.labreportsystemv2backend.exceptions;

import org.springframework.http.HttpStatus;

public class FileCodeNotFoundException extends CustomException{
    public FileCodeNotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND);
    }
}
