package com.example.demo.exception;

public class ForeignKeyConstraintException extends RuntimeException {
    public ForeignKeyConstraintException(String errorMessage){
        super(errorMessage);
    }
}
