package com.example.store.exception;

public class EmptyOrInvalidFieldException extends RuntimeException {
    public EmptyOrInvalidFieldException(String message) {
        super(message);
    }
}
