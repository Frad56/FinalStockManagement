package com.example.store.Exception;

public class ArgumentNotValidException extends RuntimeException {
    public ArgumentNotValidException(String message) {
        super(message);
    }
}
