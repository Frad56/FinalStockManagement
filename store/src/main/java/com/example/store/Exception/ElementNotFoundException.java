package com.example.store.exception;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(Long id ){
        super("Element not found with this id :"+id);
    }
    public ElementNotFoundException(String name ){
        super("Element not found with  :"+name);
    }
    public ElementNotFoundException(Long id, String information) {
        super("Element not found with id: " + id + " (" + information + ")");
    }

}
