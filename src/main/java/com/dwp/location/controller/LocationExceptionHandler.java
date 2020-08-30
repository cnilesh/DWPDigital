package com.dwp.location.controller;

import com.dwp.location.exception.DWPInvalidDataException;
import com.dwp.location.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LocationExceptionHandler {

    @ExceptionHandler(DWPInvalidDataException.class)
    public ResponseEntity<Error> handleInvalidDataException() {
        Error error = new Error();
        error.setMessage("Invalid Data found, please try with valid data");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleExceptions() {
        Error error = new Error();
        error.setMessage("Application encountered an error, please contact administrator for details");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
