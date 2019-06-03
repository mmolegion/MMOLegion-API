package com.mmolegion.core.exception;

import com.mmolegion.core.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler {

    public static ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        Map<String,String> response = new HashMap<>();
        e.printStackTrace();
        response.put("message", "A required parameter is missing from the request, please try again.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> handleIOException(IOException e) {
        Map<String,String> response = new HashMap<>();
        e.printStackTrace();
        response.put("message", "An issue occurred during the POST request.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.SERVICE_UNAVAILABLE), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
