package com.booking.globalExeption;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiException {
    private  String message;
    private  Throwable throwable;
    private  HttpStatus httpStatus;
    private  ZonedDateTime timeStamp;

    public ApiException(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime timeStamp) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }
}