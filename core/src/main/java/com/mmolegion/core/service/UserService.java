package com.mmolegion.core.service;

import com.mmolegion.core.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> findAllUsers();

    User getUserByUsername(User user);

    User getUserByEmail(User user);

    User updateUser(User user);

    void deleteUser(User user);

}
