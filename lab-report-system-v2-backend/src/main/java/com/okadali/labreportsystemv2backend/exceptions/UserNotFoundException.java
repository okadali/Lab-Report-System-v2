package com.okadali.labreportsystemv2backend.exceptions;

public class UserNotFoundException  extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
