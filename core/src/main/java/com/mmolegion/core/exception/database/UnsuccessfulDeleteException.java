package com.mmolegion.core.exception.database;

public class UnsuccessfulDeleteException extends Exception {

    public UnsuccessfulDeleteException(String errorMessage) {
        super(errorMessage);
    }

}
