package com.okadali.labreportsystemv2backend.exceptions;

import org.springframework.http.HttpStatus;

public class ReportNotFoundException extends CustomException{
    public ReportNotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND);
    }
}
