package com.mmolegion.core.service;

import com.mmolegion.core.model.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface UserService {

    User findUser(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException;

}
