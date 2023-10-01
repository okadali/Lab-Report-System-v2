package com.okadali.labreportsystemv2backend.controllers;

import com.okadali.labreportsystemv2backend.dto.other.ResponseData;
import com.okadali.labreportsystemv2backend.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({
            CustomException.class
    })
    public ResponseEntity<ResponseData> handleAllExceptions(CustomException exception) {
        ResponseData returnData = new ResponseData(false,exception.getMessage(),null);
        HttpStatus status = exception.getStatus();
        return new ResponseEntity(returnData,status);
    }
}
