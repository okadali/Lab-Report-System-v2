package com.okadali.labreportsystemv2backend.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserWrongCredsException extends CustomException {
    public UserWrongCredsException(String message) {
        super(
                message,
                HttpStatus.UNAUTHORIZED
        );
    }
}
