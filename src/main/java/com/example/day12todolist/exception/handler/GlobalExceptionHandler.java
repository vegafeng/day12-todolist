package com.example.day12todolist.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.InvalidKeyException;

/**
 * @author FENGVE
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return e.getMessage();
    }
    @ExceptionHandler(InvalidKeyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String invalidKeyException(InvalidKeyException e) {
        return e.getMessage();
    }

}
