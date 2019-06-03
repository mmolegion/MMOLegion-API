package com.mmolegion.core.exception.database;

public class UnsuccessfulUpdateException extends Exception {

    public UnsuccessfulUpdateException(String errorMessage) {
        super(errorMessage);
    }

}
