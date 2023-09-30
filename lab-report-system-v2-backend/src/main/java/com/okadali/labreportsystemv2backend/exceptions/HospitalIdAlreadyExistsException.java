package com.okadali.labreportsystemv2backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class HospitalIdAlreadyExistsException extends CustomException{
    public HospitalIdAlreadyExistsException(String message) {
        super(
                message,
                HttpStatus.CONFLICT
        );
    }
}
