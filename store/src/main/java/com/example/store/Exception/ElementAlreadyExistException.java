package com.example.store.Exception;

public class ElementAlreadyExistException extends RuntimeException{


    public ElementAlreadyExistException(String element,String name ){
        super(element+" Already Exist "+name);
    }


}
