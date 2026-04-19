package com.example.store.Exception;

public class SendEmailException extends RuntimeException {
    public SendEmailException(String message) {
        super(message);
    }
}
