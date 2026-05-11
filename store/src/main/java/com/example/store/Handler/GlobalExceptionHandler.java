package com.example.store.Handler;


import com.example.store.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<?> elementNotFound(ElementNotFoundException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ElementAlreadyExistException.class)
    public ResponseEntity<?> handlerElementAlreadyExistException(ElementAlreadyExistException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }


    @ExceptionHandler(ResourceInUseException.class)
    public ResponseEntity<?> handlerResourceInUseException(ResourceInUseException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handlerUnauthorizedException(UnauthorizedException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }


    @ExceptionHandler(SendEmailException.class)
    public ResponseEntity<?> handlerSendEmailException(SendEmailException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationCodeException.class)
    public ResponseEntity<?> handlerValidationCodeException(ValidationCodeException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handlerIllegalArgumentExceptionException(IllegalArgumentException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }


    @ExceptionHandler(ProductHasNoVariantsException.class)
    public ResponseEntity<?> handlerArgumentNotValidException(ArgumentNotValidException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }


    //ArgumentNotValidException

    @ExceptionHandler(ArgumentNotValidException.class)
    public ResponseEntity<?> handlerProductHasNoVariantsException(ProductHasNoVariantsException ex){

        Map<String,Object> error = new HashMap<>();
        error.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

}
