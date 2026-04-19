package com.example.store.Exception;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(Long id ){
        super("Element not found with this id :"+id);
    }
    public ElementNotFoundException(String name ){
        super("Element not found with  :"+name);
    }

}
