package com.medicrm.exception;

import com.appoinment.Exception.AppoinmentIdNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GloableExceptionHanderl {

    @ExceptionHandler(com.medicrm.exception.HospitalIdNotFound.class)
    public ResponseEntity<Map<String, Object>> handleHospitalNotFound(
            com.medicrm.exception.HospitalIdNotFound ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", 404);
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppoinmentIdNotFound.class)
    public ResponseEntity<Map<String, Object>> handleAppointmentNotFound(
            AppoinmentIdNotFound ex) {

        Map<String, Object> response = new HashMap<>();
        response.put("status", 404);
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}