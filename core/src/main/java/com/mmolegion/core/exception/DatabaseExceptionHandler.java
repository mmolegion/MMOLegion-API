package com.mmolegion.core.exception;

import com.mmolegion.core.exception.database.UnsuccessfulDeleteException;
import com.mmolegion.core.exception.database.UnsuccessfulUpdateException;
import com.mmolegion.core.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class DatabaseExceptionHandler {

    public static ResponseEntity<?> handleUnsuccessfulUpdateException(UnsuccessfulUpdateException e) {
        Map<String,String> response = new HashMap<>();
        e.printStackTrace();
        response.put("message", "The update query was unsuccessful.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.SERVICE_UNAVAILABLE), HttpStatus.SERVICE_UNAVAILABLE);
    }

    public static ResponseEntity<?> handleUnsuccessfulDeleteException(UnsuccessfulDeleteException e) {
        Map<String,String> response = new HashMap<>();
        e.printStackTrace();
        response.put("message", "The delete query was unsuccessful.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.SERVICE_UNAVAILABLE), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
