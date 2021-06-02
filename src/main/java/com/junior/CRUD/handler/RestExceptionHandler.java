package com.junior.CRUD.handler;

import com.junior.CRUD.exceptions.ErrorDetails;
import com.junior.CRUD.exceptions.ResourceNotFoundException;
import com.junior.CRUD.exceptions.ValidationErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {
        ErrorDetails rfnDetails = new ErrorDetails();
        rfnDetails.setDetail(rfnException.getMessage());
        rfnDetails.setStatus(HttpStatus.NOT_FOUND.value());
        rfnDetails.setTimestamp(new Date().getTime());
        rfnDetails.setTitle(" NOT FOUND");
        rfnDetails.setDeveloperMessage(rfnException.getClass().getName());

        return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidaException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        Map<String, String> map = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ValidationErrorDetails rfnDetails = new ValidationErrorDetails();

        rfnDetails.setDetail("field validator error");
        rfnDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        rfnDetails.setTimestamp(new Date().getTime());
        rfnDetails.setTitle("field validator error");
        rfnDetails.setDeveloperMessage("error validation");
        rfnDetails.setMessage(map);
        return new ResponseEntity<>(rfnDetails, HttpStatus.BAD_REQUEST);
    }

}
