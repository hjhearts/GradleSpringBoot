package com.mygradle.commons.controller;

import com.mygradle.commons.exception.UserNotFoundException;
import com.mygradle.commons.model.ApiErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorDetail> handleUserNotFoundException(UserNotFoundException unfe){
        ApiErrorDetail errorDetail = new ApiErrorDetail();
        errorDetail.setTimestamp(new Date());
        errorDetail.setCode(1062);
        errorDetail.setMessage(unfe.getMessage());
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
}
