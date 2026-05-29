package com.example.store.exception;

public class ProductHasNoVariantsException extends RuntimeException {
    public ProductHasNoVariantsException(String message) {
        super(message);
    }
}
