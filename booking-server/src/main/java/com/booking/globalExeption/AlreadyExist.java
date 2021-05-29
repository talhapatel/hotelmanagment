package com.booking.globalExeption;

public class AlreadyExist extends RuntimeException{

    public AlreadyExist(String message) {
        super(message);
    }

    public AlreadyExist(String message, Throwable cause) {
        super(message, cause);
    }
}