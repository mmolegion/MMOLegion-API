package com.mmolegion.core.exception;

import com.mmolegion.core.exception.user.AccountLockedException;
import com.mmolegion.core.exception.user.UserExistsException;
import com.mmolegion.core.exception.user.UserNotExistsException;
import com.mmolegion.core.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserExceptionHandler {

    public static ResponseEntity<?> handleJWTException(GeneralSecurityException e) {
        Map<String,String> response = new HashMap<>();
        e.printStackTrace();
        response.put("message", "There was an issue in creating the token.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> handleAccountLockedException(AccountLockedException e) {
        Map<String,String> response = new HashMap<>();
        e.printStackTrace();
        response.put("failedAttempts", String.valueOf(e.getUser().getFailedAttempts()));
        response.put("lockedOutUntil", new Date(e.getUser().getLockoutUntil()).toString());
        response.put("message", "This account has been locked out for too many incorrect password attempts. Please try again later.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    public static ResponseEntity<?> handleUserExistsException(UserExistsException e) {
        Map<String,String> response = new HashMap<>();
        e.printStackTrace();
        response.put("message", "The username and/or email address provided already exist. Please try again.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<?> handleUserNotExistsException(UserNotExistsException e) {
        Map<String,String> response = new HashMap<>();
        e.printStackTrace();
        response.put("message", "The username provided does not exist.");
        response.put("valid", "false");
        return new ResponseEntity<>(Response.createResponse(response, HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

}
