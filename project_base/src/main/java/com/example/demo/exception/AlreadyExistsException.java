package com.example.demo.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}
