package com.example.store.Exception;

public class ProductHasNoVariantsException extends RuntimeException {
    public ProductHasNoVariantsException(String message) {
        super(message);
    }
}
