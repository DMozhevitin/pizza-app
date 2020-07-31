package com.example.pizzashop.controller.handler;

import com.example.pizzashop.exception.ValidationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, ValidationException.class})
    public ResponseEntity<Object> handle(
            MethodArgumentNotValidException exception
    ) {

        Map<String, Object> body = new LinkedHashMap<>();

        String anyError = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(null);


        body.put("message", anyError);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
