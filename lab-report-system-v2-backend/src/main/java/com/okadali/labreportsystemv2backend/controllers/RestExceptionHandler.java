package com.okadali.labreportsystemv2backend.controllers;

import com.okadali.labreportsystemv2backend.dto.other.ResponseData;
import com.okadali.labreportsystemv2backend.exceptions.CustomException;
import com.okadali.labreportsystemv2backend.exceptions.HospitalIdAlreadyExistsException;
import com.okadali.labreportsystemv2backend.exceptions.UserWrongCredsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({
            UserWrongCredsException.class,
            HospitalIdAlreadyExistsException.class
    })
    public ResponseEntity<ResponseData> handleAllExceptions(CustomException exception) {
        ResponseData returnData = new ResponseData(false,exception.getMessage(),null);
        HttpStatus status = exception.getStatus();
        return new ResponseEntity(returnData,status);
    }
}
