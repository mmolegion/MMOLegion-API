package com.mmolegion.core.exception.user;

public class UserNotExistsException extends Exception {

    public UserNotExistsException(String errorMessage) {
        super(errorMessage);
    }
}
