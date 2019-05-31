package com.mmolegion.core.service;

import com.mmolegion.core.model.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface UserService {

    User findUser(String username) throws InvalidKeySpecException, NoSuchAlgorithmException;

    User createUser(String username, String email, String password) throws  InvalidKeySpecException, NoSuchAlgorithmException;

    int updateUser(User user);

    int deleteUser(User user);

    int incrementFailedAttempts(User user);

    int setUserLockout(User user);

    int clearUserLockout(User user);

}
