package com.mmolegion.core.exception.user;

import com.mmolegion.core.model.User;

public class AccountLockedException extends Exception {

    private User user;

    public AccountLockedException(String errorMessage, User user) {
        super(errorMessage);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
