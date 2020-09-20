package com.sumup.codingchallenge;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleFieldNotFoundException(
            HttpMessageNotReadableException ex) {

        Map<String, Object> body = new HashMap<>();
        String responseMessage =  String.format("JSON file is not properly formatted. %s", ex.getMostSpecificCause().getMessage());
        body.put("message", responseMessage);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}

