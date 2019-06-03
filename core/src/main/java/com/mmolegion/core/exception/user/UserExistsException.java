package com.mmolegion.core.exception.user;

public class UserExistsException extends Exception {

    public UserExistsException(String errorMessage) {
        super(errorMessage);
    }
}
