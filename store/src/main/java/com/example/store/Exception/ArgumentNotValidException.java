package com.example.store.exception;

public class ArgumentNotValidException extends RuntimeException {
    public ArgumentNotValidException(String message) {
        super(message);
    }
}
