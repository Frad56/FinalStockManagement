package com.example.store.exception;

public class ElementAlreadyExistException extends RuntimeException{


    public ElementAlreadyExistException(String element,String name ){
        super(element+" Already Exist "+name);
    }


}
